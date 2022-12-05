package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.YouTubeDto;

import java.util.List;

public interface YouTubeService {
    List<Video> getYouTubeList(String query, int num);
}
