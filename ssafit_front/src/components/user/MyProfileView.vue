<template>
<v-container>
  <v-row>
    <v-col cols="3">
      <v-card height="170" align="center" class="pa-6">

          
          <v-img circle
          height="130" width="150"
          src="https://davaooccidental.gov.ph/wp-content/uploads/2017/07/profile_img.png"
          aspect-ratio="1"
          class="grey lighten-2"
          ></v-img>
      </v-card>
    </v-col>
    <v-col cols="9">
      <v-card height="170">
        <v-card-title>{{loginUser.name}}</v-card-title>
        <v-card-text>
          <p>닉네임 : {{loginUser.nickname}}</p>  
          <p>가입일 : {{loginUser.regDate}}</p>
          <p>취미 : {{loginUser.favorite}}</p>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
  <v-row>
    
  </v-row>
  <v-row justify="center" absolut>
      <v-col >
        <v-tabs  grow >
          <v-tab :value=val   v-for="[icon, val, to ] in links" :key="val" :to="{name : to}"><v-icon>{{icon}}</v-icon></v-tab> 
        </v-tabs>
      </v-col>
    </v-row> 

       <router-view></router-view>
      </v-container>
</template>

<script>
export default {

    name:'profileView',
    data(){
      return{
        drawer:null,
        loginUser:JSON.parse(sessionStorage.getItem('loginUser')),
        links:[
          ['mdi-account-multiple','friend','friend'],
          ['mdi-heart-multiple','following','following'],
          ['mdi-hand-heart','follower','follower']
        ]
      }
    }
}
</script>

<style>

</style>