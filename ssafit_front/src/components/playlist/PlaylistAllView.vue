<template>
  <v-container>
    <h1>
      {{name}}님의 플레이리스트
    </h1>
    <v-btn class="ma-2" color="primary darken-2" dark :to="{ name: 'playlistCreate' }">
      새 플레이리스트<br />
    </v-btn>
    <br><br><br>
    <v-row outlined v-for="{ playlistTitle, playlistId, content, userId, open,likeCnt } in playlists" :key="playlistId">
      <!-- :to="{name : 'playlistDetail'}"  -->

      <v-col cols="12">
        <v-card height="120" hover @click="movePage({ playlistTitle, playlistId, userId, open, content })">
          <!--파라미터로 넘기는 것도 해야함 -->
          <v-row>
            <v-col cols="2" align-self="center">
              <v-img src="https://d2v80xjmx68n4w.cloudfront.net/gigs/GgTmM1625215562.jpg" max-height="120" class="ml-2">
              </v-img>
            </v-col>
            
            <v-col cols="7" align-self="center">
              <v-card-title>{{ playlistTitle }}</v-card-title>
              <v-card-subtitle>{{ content }} </v-card-subtitle>
              
            </v-col>

            <v-col cols="3" align-self="center">
              <!-- 좋아요 버튼 -->
              <v-btn class="ma-2" color="primary darken-2" dark>
                <v-icon dark left text>
                  mdi-thumb-up
                </v-icon>
                
                {{likeCnt}}
              </v-btn>
              <v-btn color="success">{{ open == 1 ? 'OPEN' : 'PRIVATE' }}</v-btn>
            </v-col>

          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script type="module">

import { mapState } from 'vuex';
export default {
  name: 'playlistAllView',
  data() {
    return {
      userId: JSON.parse(sessionStorage.getItem('loginUser'))['userId'],
      name: JSON.parse(sessionStorage.getItem('loginUser'))['name']
    }
  },
  methods: {
    like() {
      alert("좋아요를 눌렀습니다.")
    },
    movePage(data) {
      this.$store.dispatch("showPlaylist", data)
      this.$store.dispatch("getComments", data)
      this.$router.push({name:'playlistDetail'})
    }
  },
  computed: {
    ...mapState(['playlists'])
  },
  mounted() {
    console.log("mounted" + JSON.parse(sessionStorage.getItem('loginUser'))['userId'])
    this.$store.dispatch("getMyPlaylists", JSON.parse(sessionStorage.getItem('loginUser'))['userId'])
  }

}
</script>

<style>

</style>