<template>
  <v-container>

    

    <!--검색창 start-->
    <v-form @submit.prevent="search">

      <v-text-field label="검색어를 입력하세요" prepend-inner-icon="mdi-magnify" type="search" v-model="input">
      </v-text-field>

      <p class="font-weight-light">{{inputText}}</p>
      <v-divider></v-divider>
    </v-form>
    <!--검색창 end-->
    


    <!--결과창 start-->
    <v-row outlined v-for="{title, thumbnailPath, videoId, likeCnt} in searchVideoList" :key="videoId" >
      <!-- :to="{name : 'playlistDetail'}"  -->

      <v-col cols="12">
        <v-card height="150" hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})">
          <!--파라미터로 넘기는 것도 해야함 -->
          <v-row>
            <v-col cols="3" align-self="center">
              <v-img :src=thumbnailPath  height="130" width="100%" class="ml-2">
              </v-img>
            </v-col>

            <v-col cols="9" align-self="center">
                <h4 v-html="title"></h4>
                <br>
                  <v-icon left color="red">
                    mdi-heart
                  </v-icon> LIKE {{likeCnt > 0 ? likeCnt : 0}}
            </v-col>

            

          </v-row>
        </v-card>
      </v-col>
    </v-row>

    <!--결과창 end-->

  </v-container>
</template>

<script type="module">
import { mapState } from 'vuex'
export default {
  name: 'searchResultView',
  data() {
    return {
      input: "",
      inputText:""
    }
  },
  methods: {
    search() {

      if (this.input === "") {
        this.inputText= '아무것도 입력 안함'
      } else {
        this.inputText= ` "${this.input}" 검색 결과`
        this.$store.dispatch("searchVideo",this.inputText+"운동")
      }
    },
    movePage(video){
      console.log(video)
    let userId = JSON.parse(sessionStorage.getItem('loginUser'))['userId']

      this.$store.dispatch("saveVideo",video) // 저장하기
      this.$store.dispatch("getVideo",video.videoId) // 바로 가져오기 
      this.$store.dispatch("getLikeVideo",userId)
      this.$router.push({name:'videoDetail'})
    }
  },
  computed:{
    ...mapState(['searchVideoList','searchText'])
  }
}
</script>

<style>

</style>