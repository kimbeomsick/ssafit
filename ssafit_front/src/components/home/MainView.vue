<template>
  <v-container>



      <!-- 음악 리스트 -->  
      <br><br><br> 
      <p class="text-h4 pb-3">#운동할 때 듣기 좋은 음악</p>
      <v-row height="200">
        <v-col cols="3" v-for="{title, thumbnailPath, videoId, likeCnt, isLike} in musicVideoList" :key="videoId">
          <v-card hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})" class="mx-auto" max-width="400">
            <v-img class="white--text align-end" height="150px" :src=thumbnailPath>
            </v-img>

            <v-card-text class="pb-0" v-html="title">
              
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>


      <!-- 피트니스 리스트 -->  
      <br><br>
      <p class="text-h4 pb-3">#피트니스 영상</p>
      <v-row height="200">
        <v-col cols="3" v-for="{title, thumbnailPath, videoId, likeCnt,  isLike} in fitnessVideoList" :key="videoId">
          <v-card hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})" class="mx-auto" max-width="400">
            <v-img class="white--text align-end" height="150px" :src=thumbnailPath>
            </v-img>

            <v-card-text class="pb-0" v-html="title">
              
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>


      <!-- 스포츠 리스트 -->    
      <br><br><br>
      <p class="text-h4 pb-3">#스포츠 뉴스</p>
      <v-row height="200">
        <v-col cols="3" v-for="{title, thumbnailPath, videoId, likeCnt, isLike} in sportNewsList" :key="videoId">
          <v-card hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})" class="mx-auto" max-width="400">
            <v-img class="white--text align-end" height="150px" :src=thumbnailPath>
            </v-img>

            <v-card-text class="pb-0" v-html="title">
              
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>

      
      <!-- 음악 리스트 -->   
      <br><br><br> 
      <p class="text-h4 pb-3">#취향저격 영상</p>
      <v-row height="200">
        <v-col cols="3" v-for="{title, thumbnailPath, videoId, likeCnt, isLike} in favoriteVideoList" :key="videoId">
          <v-card hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})" class="mx-auto" max-width="400">
            <v-img class="white--text align-end" height="150px" :src=thumbnailPath>
            </v-img>

            <v-card-text class="pb-0" v-html="title">
              
            </v-card-text>

          </v-card>
        </v-col>
      </v-row>


  </v-container>
</template>

<script type="module">

import { mapState } from 'vuex'
export default {
  name: 'mainView',
  data() {
    return {

    }
  },
  methods:{
    movePage(video){
      console.log(video)
    let userId = JSON.parse(sessionStorage.getItem('loginUser'))['userId']

      this.$store.dispatch("saveVideo",video) // 저장하기
      this.$store.dispatch("getVideo",video.videoId) // 바로 가져오기 
      this.$store.dispatch("getLikeVideo",userId)
      this.$router.push({name:'videoDetail'})
    }
  },
  computed: {
    ...mapState(['musicVideoList', 'loginUser', 'fitnessVideoList', 'sportNewsList', 'favoriteVideoList'])
  },
  created() {
    //로그인한 유저가 없다면 다시 로그인 화면으로..
    if (this.loginUser == null) {
      console.log("로그인 실패")
      this.$router.push({ name: 'login' })
    } else {
      console.log("로그인에 성공했습니다.")
    }
  },
  mounted() {
    this.$store.dispatch('musicVideo')
   this.$store.dispatch('fitnessVideo')
   this.$store.dispatch('sportVideo')
   this.$store.dispatch('favoriteVideo')

  }



}
</script>

<style>

</style>