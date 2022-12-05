package com.ssafy.ssafit.model.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.YouTubeDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class YouTubeServiceImpl implements YouTubeService {

    private static String PROPERTIES_FILENAME = "youtube.properties";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static long NUMBER_OF_VIDEOS_RETURNED = 10;

    /**
     * Global instance of Youtube object to make all API requests.
     */
    private static YouTube youtube;
    private static final String API_KEY = "AIzaSyCo3-gvKF4vf7Z2F-URC65YR0nNljXPP6o";
//    private static final String API_KEY = "AIzaSyBmkf7bxlEfQzAsJYNYiWfogQlqfLn_ldM";
//    private static final String API_KEY = "AIzaSyAR6SUnt2HTzkarjOZL9cEa-mvuWKhfxQ4";
//    private static final String API_KEY ="AIzaSyDiNzLTUNGXCODw0YbvWgSOJoC8ZYMS0Xs";
//    private static final String API_KEY="AIzaSyD-eq3-8oJgR1NK_XMlmfSFpJqCVPnkG5I";
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Double checks the kind is video.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }

    @Override
    public List<Video> getYouTubeList(String query, int num) {
        NUMBER_OF_VIDEOS_RETURNED = num;
        List<Video> videoList = new ArrayList<>();
        try {
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("ssafit_back").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(API_KEY);
            search.setQ(query);
            search.setType("video");
            /*
             * Below method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), query);
                convertRawVideo2VideoDto(videoList, searchResultList.iterator());
                return videoList;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return videoList;
    }

    public void convertRawVideo2VideoDto(List<Video> list, Iterator<SearchResult> iteratorSearchResults) {
        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Double checks the kind is video.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");

                Video video = new Video(rId.getVideoId(), singleVideo.getSnippet().getTitle(), thumbnail.getUrl());

                list.add(video);
            }
        }
    }
}
