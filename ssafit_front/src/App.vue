

<template>
  <v-app id="inspire">
<!-- 상단 메뉴 -->
  <v-row justify="center" v-show="$vuetify.breakpoint.mdAndDown" v-if="loginUser" absolute>
      <v-col >
        <v-tabs  centered >
          <v-tab :value=val   v-for="[icon, val, to ] in links" :key="val" :to="{name : to}"><v-icon>{{icon}}</v-icon></v-tab> 
        </v-tabs>
      </v-col>
    </v-row> 
<!-- 상단 메뉴 끝-->

<!-- 사이드 메뉴바 -->
    <v-navigation-drawer
    v-if="loginUser"
      v-model="drawer"
      app
    >

      <v-sheet
        color="grey lighten-4"
        class="pa-4"
      >


        <div>[{{loginUser.nickname}}]님 환영합니다.</div>
      </v-sheet>

      <v-divider></v-divider>

      <v-list>
        <v-list-item
          v-for="[icon, text, to] in links"
          :key="icon"
          :to="{name:to}"
        >
          <v-list-item-icon>
            <v-icon>{{ icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title >{{ text }}</v-list-item-title>
          </v-list-item-content>

        </v-list-item>
        <!-- 독립된 logout 버튼 -->
       
      </v-list>
    </v-navigation-drawer>
    <!-- 사이드 메뉴바 -->

<!-- 가운데 메인 화면 -->
    <v-main>

      <v-container
        class="pa-7"
        fluid
      >
      
       <router-view></router-view> 
      </v-container>
    </v-main>
    <!-- 가운데 메인 화면 끝 -->
  </v-app>

  
</template>

<script type="module">
import {mapState} from 'vuex'

  export default {
    name:"App",
    data: () => ({
      drawer: null,
      links: [
        ['mdi-home', 'Home', 'home'], // 링크 나중에 넣어줘야함
        ['mdi-magnify', 'Search', 'search'],
        ['mdi-animation-play', 'Playlist', 'playlist'],
        ['mdi-heart', 'LikeVideo','likeList'],
        ['mdi-earth', 'friend', 'friend' ],
        ['mdi-cancel', 'logout','logout']
      ],
    }),
    computed:{
      ...mapState(['loginUser'])
    }
  }
</script>

