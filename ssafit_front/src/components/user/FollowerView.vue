<template>
  <v-container>
    <h1>팔로워</h1>
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
          name: '박승현',
          img: `https://picsum.photos/500/300?image=${16}`
        },
        {
          name: '손준석',
          img: `https://picsum.photos/500/300?image=${17}`
        },
        {
          name: '고예준',
          img: `https://picsum.photos/500/300?image=${26}`
        },
        {
          name: '김민우',
          img: `https://picsum.photos/500/300?image=${21}`
        },
        {
          name: '마지훈',
          img: `https://picsum.photos/500/300?image=${22}`
        },
        {
          name: '마건우',
          img: `https://picsum.photos/500/300?image=${36}`
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