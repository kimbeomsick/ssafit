<template>
  <v-container>
    <h1>팔로잉</h1>
    <v-row>
      <v-col cols="3" v-for="{name, img} in data" :key="img">
        <v-card hover>
          <v-img @click="movePage(n)"
            :src="img"
            class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="200px">
            <v-card-title>{{ name }}</v-card-title>
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
  data() {
    return {
      data: [
        {
          name: '박종수',
          img: `https://picsum.photos/500/300?image=${15}`
        },
        {
          name: '손현우',
          img: `https://picsum.photos/500/300?image=${12}`
        },
        {
          name: '고준영',
          img: `https://picsum.photos/500/300?image=${23}`
        },
        {
          name: '김영식',
          img: `https://picsum.photos/500/300?image=${24}`
        },
        {
          name: '마영철',
          img: `https://picsum.photos/500/300?image=${25}`
        },
        {
          name: '마이클',
          img: `https://picsum.photos/500/300?image=${26}`
        },
        
      ]
    }
  },
  methods: {
    cancel(n) {
      this.data.splice(n, 1)
    }
  },
  movePage(n) {
    alert(n + "번째 여우의 플레이 리스트로 가야함")
  }
}

</script>

<style>

</style>