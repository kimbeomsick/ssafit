<template>
  <v-container>
      <h1>친구 목록</h1>
      <v-row>
        <v-col cols="3" v-for="name, n in data" :key="n" >
        <v-card hover >
          <v-img @click="movePage(n)"
          :src="`https://picsum.photos/500/300?image=${n * 3 + 10}`"
          class="white--text align-end"
          gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          height="200px"
          
          >
          <v-card-title >{{name}}</v-card-title>
        </v-img>
        
        <v-card-actions>
          <v-spacer></v-spacer>
          
          <v-btn icon @click="cancel(n)">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          
        </v-card-actions>
      </v-card>
    </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data(){
    return{
      data:['에디슨','토마스','이순신','강감찬','손석구','마동석','마이클']
    }
  },
  methods:{
    cancel(n){
      this.data.splice(n,1)
    }
    },
    movePage(n){
      alert(n+"번째 여우의 플레이 리스트로 가야함")
    }
  }

</script>

<style>

</style>