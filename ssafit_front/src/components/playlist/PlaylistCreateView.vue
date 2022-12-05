<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
          >
            Open Dialog
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="text-h5">User Profile</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="플레이리스트 제목*"
                    required
                    v-model="playlist.playlistTitle"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="설명*"
                    required
                    v-model="playlist.content"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-select
                    :items="['공개', '비공개']"
                    label="공개 여부*"
                    required
                  ></v-select>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-autocomplete
                    :items="['스트레칭', '바디빌딩', '파워리프팅', '필라테스', '식단', '유머', '음악', '기타']"
                    label="태그"
                    multiple
                  ></v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue darken-1"
              text
              @click="cancel"
            >
              Close
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="create"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>
  
  <script>
  
  
  export default {
      data(){
          return{
              dialog:true,
              playlist: {
                playlistTitle: "",
                content: "",
                userId: "",
              }
          }
      },
      methods:{
          cancel(){
              this.$router.go(-1)
          },
          create(){
              this.$store.dispatch("createPlaylist", this.playlist)          
          }
      },
      mounted(){
        this.playlist.userId = JSON.parse(sessionStorage.getItem('loginUser'))['userId']
      }
  }
  </script>
  
  <style>
  
  </style>