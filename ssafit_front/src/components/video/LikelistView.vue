<template>
  <v-container>
    <p class="text-h5 pb-3">#좋아요 표시한 동영상</p>
      <v-row height="200">
        <v-col cols="3" v-for="{title, thumbnailPath, videoId, likeCnt, isLike} in likeVideoList" :key="videoId">
          <v-card hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})" class="mx-auto" max-width="400">
            <v-img class="white--text align-end" height="150px" :src=thumbnailPath>
            </v-img>

            <v-card-text class="pb-0">
              {{title}}
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>
  </v-container>
</template>

<script type="module">
import { mapState } from 'vuex';

export default {
  name:'likelistView',
  data(){
    return{
      
    }
  },
  computed:{
   ...mapState(['loginUser','likeVideoList'])
  },
  mounted(){
    console.log("좋아하는 비디오 불러오기")

    let userId = JSON.parse(sessionStorage.getItem('loginUser'))['userId']
    this.$store.dispatch("getLikeVideo",userId)

  },
  methods:{
    movePage(video){
      console.log(video)
      this.$store.dispatch("saveVideo",video) // 저장하기
      this.$store.dispatch("getVideo",video.videoId) // 바로 가져오기 
      this.$router.push({name:'videoDetail'})
    }
  },
}
</script>

<style>

</style>