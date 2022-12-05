<template>
  <v-container>
    <v-row>
        <!-- 좋아요, -->
        <v-col cols="12">
            <v-card>
          
                <iframe width="100%" height="480" :src="'https://www.youtube.com/embed/'+videoDetail.videoId" title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn icon  @click="likeVideo" v-show="videoDetail.isLike == 0"> <!-- 좋아요 내가 안누름 -->
                <v-icon>mdi-heart</v-icon><span>{{videoDetail.likeCnt}}</span>
              </v-btn>
              <v-btn icon color="primary" @click="dislikeVideo" v-show="videoDetail.isLike == 1"> <!-- 좋아요 내가 누름 -->
                <v-icon>mdi-heart</v-icon><span>{{videoDetail.likeCnt}}</span>
              </v-btn>
               

              
                <v-col cols="auto">
      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }" >
          <v-btn
            color="primary"
            v-bind="attrs"
            v-on="on"
          >Bookmark</v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="primary"
              dark
            >Opening from the bottom</v-toolbar> 
            <v-card-text>
              <br>
              <v-row  v-for="{ playlistTitle,isMine,playlistId } in playlists" :key="playlistTitle" hover>
                
                <v-col cols="12">
                  <v-card outlined class="pa-3" hover>
                  <div @click="addVideoIntoPlaylist(playlistId)" v-show="isMine == 1">{{playlistTitle}}</div>
                </v-card>
                </v-col>
              </v-row>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                text
                @click="dialog.value = false"
              >Close</v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
           
            
        </v-card-actions>
          </v-card>
        </v-col>
    </v-row>
    <div>
                
            </div>

  </v-container>
</template>

<script type="module">

import { mapState } from 'vuex';
export default {
    name: 'videoDetailView',
    data(){
        return{
           data:{
             videoId:"",
             playlistId:""
           }
        }
    },
    computed:{
        ...mapState(['videoDetail','loginUser','playlists'])
    },
    methods:{
        likeVideo(){
            this.$store.dispatch("addLikelist")
        },
        dislikeVideo(){
            this.$store.dispatch("cancelLikelist")
        },
        
        addVideoIntoPlaylist(playlistId){
          this.data.playlistId = playlistId;
          console.log(this.data)
          this.$store.dispatch('playlistAddVideo',this.data)
          this.dialog.value = false
        }


    },
    mounted(){
      let userId = JSON.parse(sessionStorage.getItem("loginUser"))['userId']
        this.$store.dispatch("getMyPlaylists",userId)

        this.data.videoId = this.videoDetail.videoId

        
    }
    
}   
</script>

<style>

</style>



