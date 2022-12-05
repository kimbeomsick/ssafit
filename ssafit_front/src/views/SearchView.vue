<template>
  <v-container>
    <h1>Search</h1>

    <v-row justify="center" grow>
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
  data(){
    return{
      links:[
          ['mdi-play-box-outline','search','search'],
          ['mdi-play-box-multiple-outline','follplaylistSearchower','playlistSearch']
        ]
    }
  }
}
</script>

<style>

</style>