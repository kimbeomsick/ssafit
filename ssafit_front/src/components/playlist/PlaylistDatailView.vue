<template>
  <v-container>
    <!-- 비디오 화면 -->
    <v-row>
      <v-col></v-col>
    </v-row>
    <v-row>
      <v-col cols="12" height="580px">
        <v-card class="pa-1">

          <iframe width="100%" height="480" :src="'https://www.youtube.com/embed/' + `${playlistVideo.videoId}`"
            title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>


        </v-card>

        <v-row justify="space-btween" class="pa-3">
          <v-col cols="4" align-self="center">
            <h2>동영상 제목</h2>
          </v-col>
          <v-col cols="6"></v-col>
          <v-col cols="2" align-self="center">

            <v-btn color="primary" @click="videoLike(videoId)">
              <v-icon left>
                mdi-heart
              </v-icon>
              Like
            </v-btn>

          </v-col>
        </v-row>
        <!-- 좋아요 버튼 -->

      </v-col>
    </v-row>
    <!-- 비디오 화면 -->



    <!-- 댓글 입력화면 start-->
    <v-row>
      <v-col cols="12">
        <v-form @submit.prevent="commentInsert">
          <v-text-field class="mx-2" label="댓글 입력" rows="1" prepend-icon="mdi-comment" type="search"
            v-model="inputValue"></v-text-field>
        </v-form>
      </v-col>

    </v-row>
    <!-- 댓글 입력 화면 end-->

    <v-row>
      <v-col cols="12">
        <v-card outlined class="pa-2">
          <v-card-text v-for="{ parent, children } in commentList" :key="parent.commentId">
            <!-- 부모댓글 -->
            <span>{{ parent.userId }}</span>

            <p>{{ parent.content }} </p>
            <v-row justify="end">



              <!-- 수정 버튼 start-->
              <v-btn text @click="commentUpdate(parent.commentId)" v-show="myId === parent.userId">
                수정
              </v-btn>
              <!-- 수정버튼 end-->


              <!-- 답글버튼 start -->
              <v-btn text color="success" @click="commentInsert(parent.commentId)" v-show="myId === parent.userId">
                답글
              </v-btn>
              <!-- 답글버튼 end -->

              <!-- 삭제버튼 start-->
              <v-btn color="error" text @click="commentDelete(parent.commentId)" v-show="myId === parent.userId">
                삭제
              </v-btn>
            </v-row>


            <v-card-text class="ml-5" v-for="{ userId, commentId, content } in children" :key="commentId">
              <!-- 자식댓글 -->
              <v-divider></v-divider>
              <span>{{ userId }}</span>
              <p class="ml-5">{{ content }}</p>
              <v-row align="center" justify="end">
                <v-btn text v-show="myId === userId" @click="commentUpdate(commentId)">수정</v-btn>
                <v-btn color="error" v-show="myId === userId" text @click="commentDelete(commentId)">
                  삭제
                </v-btn>
              </v-row>

            </v-card-text>
          </v-card-text>
        </v-card>

      </v-col>
    </v-row>




    <!-- 재생목록 start-->
    <v-navigation-drawer app clipped right v-model="drawer">
      <v-list>
        <v-list-item v-for="{ thumbnailPath, videoId, title } in playlist.Videos" :key="title" link>
          <v-list-item-content @click="showVideo(videoId, thumbnailPath)">
            <v-card outlined height="auto" hover>
              <v-img height="120px" width="100%" :src=thumbnailPath>
              </v-img>
              <v-card-title>{{ title }}</v-card-title>
            </v-card>

          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <!-- 재생목록 end-->


    <v-fab-transition>
      <v-btn v-show="$vuetify.breakpoint.mdAndDown" color="primary" dark fixed top right fab @click="drawer = !drawer">
        <v-icon>mdi-format-list-bulleted</v-icon>
      </v-btn>
    </v-fab-transition>
  </v-container>

</template>

<script type="module">
import { mapState } from 'vuex'
export default {
  data() {
    return {
      drawer: true,
      inputValue: "",
      comments : this.commentList,
      myId: JSON.parse(sessionStorage.getItem('loginUser'))['userId']
    }
  },
  computed: {
    ...mapState(['playlistVideo', 'commentList', 'playlist'])
  },
  methods: {
    refresh(){
      let data = {
      userId: JSON.parse(sessionStorage.getItem("loginUser"))['userId'],
      playlistId: this.playlist.playlist_info.playlistId
    }
    this.$store.dispatch("getComments", data)
    },
    commentInsert() {
      let data = {
        'content': this.inputValue,
        'parentId': -1,
        'playlistId': this.playlist.playlist_info.playlistId,
        'userId': JSON.parse(sessionStorage.getItem('loginUser'))['userId']
      }
      this.$store.dispatch("commentInsert", data)
      this.inputValue = ""
      this.refresh()

    },
    commentDelete(commentId) {
      let data = {
        'userId':this.myId,
        'commentId':commentId
      }
    this.$store.dispatch("commentDelete",data)

      
      this.refresh()
    },
    commentUpdate(id) {
      let value = prompt(`${id}번째 comment수정`)
      if (confirm(`${id}번째 comment를 ${value}로 수정하시겠습니까?`)) {
        alert("수정되었습니다 -> 메서드 구현해야함")
      }
      this.refresh()
    },

    showVideo(a, b) {
      console.log(a)
      console.log(b)
      console.log(this.$store.state.playlistVideo)
      console.log(this.$store.state.playlists)
      console.log(this.playlistVideo)
      console.log(this.commentList)

    }
  },
  created() {
    let data = {
      userId: JSON.parse(sessionStorage.getItem("loginUser"))['userId'],
      playlistId: this.playlist.playlist_info.playlistId
    }
    this.$store.dispatch("getComments", data)
  }
}
</script>

<style>

</style>