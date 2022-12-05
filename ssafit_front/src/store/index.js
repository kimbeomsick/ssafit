import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

const COMMENT_REST_API = `http://localhost:9999/api/comments`
// const FOLLOW_REST_API = `http://localhost:9999/api/follows`
const PLAYLIST_REST_API = `http://localhost:9999/api/playlists`
const VIDEO_REST_API = `http://localhost:9999/api/videos`
const USER_REST_API = `http://localhost:9999/api/users`

export default new Vuex.Store({
  state: {
    loginUser: null,
    hotPlaylists:[],
    searchVideoList: [],
    searchText: "",
    musicVideoList: [],
    fitnessVideoList: [],
    sportNewsList: [],
    favoriteVideoList: [],
    videoDetail: {},
    likeVideoList:[],

    //////김범식 추가함
    playlist:null, //playlist에 대한 정보
    playlistVideo:null, //거기에 속한 단 하나의 비디오
    playlists:[], //playlist 여러개
    commentList:null, //댓글
    /////김범식 추가함
  },
  getters: {
    
  },
  mutations: {
    LOGIN_USER(state, payload) {
      state.loginUser = payload

    },
    SEARCH_VIDEOLIST(state, payload) {
      state.searchVideoList = payload
    },

    LOGOUT_USER(state, payload) {
      state.loginUser = payload
    },

    MUSIC_VIDEO(state, payload) {
      state.musicVideoList = payload
    },
    FITNESS_VIDEO(state, payload) {
      state.fitnessVideoList = payload
    },
    SPORT_VIDEO(state, payload) {
      state.sportNewsList = payload
    },
    FAVORIT_VIDEO(state, payload) {
      state.favoriteVideoList = payload
    },
    SET_VIDEO_DETAIL(state, payload) {
      state.videoDetail = payload
    },
    REFRESH_VIDEO(state,payload){
      state.videoDetail.likeCnt = payload.likeCnt
      state.videoDetail.isLike = payload.isLike
    },
    LIKE_VIDEOS(state,payload){
      state.likeVideoList=payload
    },
    ///김범식 추가함
    SET_PLAYLIST(state, payload){
      state.playlist = payload
    },
    SET_PLAYLISTS(state, payload){
      state.playlists = payload
    },
    SET_COMMENTS(state, payload){
      state.commentList = payload
    },
    SET_PLAYLIST_VIDEO(state, payload){
      state.playlistVideo = payload
    }
    ///김범식 추가함

    /// 인덕 추가

    ///
  },
  actions: {
    ///////////////////////////////////////////////////////////////
    //로그인
    login({ commit }, user) {
      const API_URL = `${USER_REST_API}/login`
      axios({
        url: API_URL,
        method: "POST",
        params: user
      }).then((res) => {
        commit
        alert("환영합니다 " + res.data.message.data.name + "님!")
        console.log(res.data)
        sessionStorage.setItem('loginUser', JSON.stringify(res.data.message.data))
        sessionStorage.setItem("access-token", res.data["access-token"])
        commit("LOGIN_USER", res.data.message.data)

        let loginUser = res.data.message.data
        loginUser.userPassword= ""
        sessionStorage.setItem('loginUser',JSON.stringify(loginUser))

        router.push({ name: 'home' })
      }).catch(err => {
        console.log(err)
        alert(err.response.data.message.message) // 컨트롤러에서 body에 바로 메시지 대신 해시맵 안에 "message": message해서 이렇게 됨
      })
    },

    //회원가입
    signup({ commit }, user) {
      const API_URL = `${USER_REST_API}/auth`
      axios({
        url: API_URL,
        method: "POST",
        data: user
      }).then((res) => {
        commit
        console.log(res)
        //alert(this.state.loginUser.name + "님, 회원가입을 축하합니다.")
        //위는 type error를 냄; state는 비동기적으로 동작하고 렌더링(마운팅)하기 전에도 동작. state는 정의되지 않았다고 하는데..
        alert("회원가입을 축하합니다!")
        router.replace({ name: 'home' })
      }).catch(err => {
        alert(err.response.data.message)
        console.log(err)
      })
    },

    //로그아웃
    logout({ commit }) {
      // commit("LOGOUT_USER", null)
      commit('LOGIN_USER',null);
      sessionStorage.removeItem("loginUser")
      router.push({ name: 'login' })
    },

    ///////////////////////////////////////////////////////////////playlist
    // 새 플리 만들기; 미완성
    createPlaylist({commit}, playlist) {
      const API_URL = `${PLAYLIST_REST_API}/my`
      console.log("createPlaylist start")
      console.log(playlist)
      axios({
        url: API_URL,
        method: "POST",
        data: playlist,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        commit
        console.log("등록 성공")
        console.log(res)
        alert("플레이리스트가 생성되었습니다.")
        router.push({ name: 'playlist' })
      }).catch(err => {
        alert(err.response.data.message)
        console.log(err)
      })
    },

    getMyPlaylists({commit}, userId) {
      const API_URL = `${PLAYLIST_REST_API}/my`
      console.log("getMyPlaylists start")
      axios({
        url: API_URL,
        method: "GET",
        params: {
          userId : userId
        },
        header: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log("success; getMyPlaylists")
        console.log(res.data.data)  
        commit("SET_PLAYLISTS", res.data.data)
      }).catch(err => {
        console.log(err)
      })
    },

    showPlaylist({commit}, data) {
      const API_URL = `${PLAYLIST_REST_API}/${data.playlistId}`
      console.log("showPlaylist start")
      axios({
        url: API_URL,
        method: "GET",
        params: {
          playlistId: data.playlistId,
          userId: data.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log(res.data.data)
        commit("SET_PLAYLIST", res.data.data)
        commit("SET_PLAYLIST_VIDEO", res.data.data.Videos[0])
      }).catch(err => {
        console.log(err)
      })
    },

    getComments({commit}, data) {
      const API_URL = `${COMMENT_REST_API}`
      console.log("getComments start")
      axios({
        url: API_URL,
        method: "GET",
        params: {
          playlistId: data.playlistId,
          userId: data.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log(res.data.data)
        commit("SET_COMMENTS", res.data.data)
      }).catch(err => {
        console.log(err)
      })
    },
///////////////////////////////////////////////////////////////video
    //youtube API를 이용한 비디오 검색
    searchVideo({ commit }, inputText) {
      const API_URL = `${VIDEO_REST_API}/search/${inputText}`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          num: 12,
          userId: this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log(res.data.data)
        commit("SEARCH_VIDEOLIST", res.data.data)
      })

    },

    //메인화면에서 띄우는 맞춤형 비디오 리스트
    musicVideo({ commit }) {
      const API_URL = `${VIDEO_REST_API}/search/쇠힙`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          num: 4,
          userId: this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log("음악 찾기")
        console.log(res)
        commit("MUSIC_VIDEO", res.data.data)
      })
    },

    //운동 맞춤 영상
    fitnessVideo({ commit }) {
      const API_URL = `${VIDEO_REST_API}/search/fitness`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          num: 4,
          userId: this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log("운동 찾기")
        console.log(res.data.data)
        commit("FITNESS_VIDEO", res.data.data)
      })
    },

    //스포츠뉴스 맞춤영상
    sportVideo({ commit }) {
      const API_URL = `${VIDEO_REST_API}/search/sportnews`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          num: 4,
          userId: this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log("스포츠 찾기")
        console.log(res.data.data)
        commit("SPORT_VIDEO", res.data.data)
      })
    },


    //좋아하는 운동 맞춤영상
    favoriteVideo({ commit }) {
      const API_URL = `${VIDEO_REST_API}/search/${this.state.loginUser.favorite}`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          num: 4,
          userId: this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log("내취향 찾기")
        console.log(res.data.data)
        commit("FAVORIT_VIDEO", res.data.data)
      })
    },


    //video 하나가져오기
    getVideo({ commit }, videoId) {
      console.log("getVideo 동작")

      const API_URL = `${VIDEO_REST_API}`
      axios({
        url: API_URL,
        method: 'GET',
        params: {
          'videoId': videoId,
          'userId':this.state.loginUser.userId
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        commit('SET_VIDEO_DETAIL',res.data.data);
        console.log(res)
      })
    },

    //비디오 저장하기
    saveVideo({commit},video){
      console.log("saveVideo 동작")
      const API_URL = `${VIDEO_REST_API}`
      axios({
        url:API_URL,
        method:'POST',
        data:{
          'title': video.title,
          'videoId': video.videoId,
          'thumbnailPath': video.thumbnailPath,
          'isLike': video.isLike,
          'likeCnt': video.likeCnt,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res=>{
        commit
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    },

    //비디오 좋아요 추가
    addLikelist({ commit }) {
      console.log("addLikelist 동작")
      // let userId = this.state.loginUser.userId;
      const API_URL = `${VIDEO_REST_API}/like`
      // let video = this.state.videoDetail;
      axios({
        url: API_URL,
        method: 'PUT',
        params:{
          'videoId':this.state.videoDetail.videoId,
          'userId': this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log(res)
        commit('REFRESH_VIDEO',res.data.data);
      }).catch(err => {
        console.log(err)
      })
    },


    //비디오 좋아요 취소
    cancelLikelist({ commit }) {
      console.log("cancelLikelist 동작")
      const API_URL = `${VIDEO_REST_API}/like`

      axios({
        url: API_URL,
        method: 'DELETE',
        params: {
          'videoId': this.state.videoDetail.videoId,
          'userId': this.state.loginUser.userId,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        commit('REFRESH_VIDEO',res.data.data);
        console.log(res)
      }).catch(err => {
        console.log(err)
      })

    },


    //좋아요 누른 비디오 가져오기
    getLikeVideo({commit},userId){
      console.log("getLikeVideo 동작")
      const API_URL = `${VIDEO_REST_API}/like`
      axios({
        url:API_URL,
        method:"GET",
        params:{
          'userId':userId
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res=>{
        commit
        console.log(res.data.data)
        commit("LIKE_VIDEOS",res.data.data)
      }).catch(err=>{
        console.log(err)
      })
    },


    //플레이 리스트에 비디오 추가하기
    playlistAddVideo({commit},data){
      const API_URL = `${VIDEO_REST_API}/videos/playlist`
      console.log("-------")
      console.log(data)
      console.log("-------")

      axios({
        url:API_URL,
        method:'PUT',
        params:{
          'playlistId':data.playlistId,
          'videoId':data.videoId
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res=>{
        commit
        console.log(res)
      })

    },
    commentInsert({commit},data){
      console.log("댓글 작성 실행")
      const API_URL = `${COMMENT_REST_API}`
      axios({
        url:API_URL,
        method:'POST',
        params:data,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }

      }).then(res=>{
        commit
        console.log(res)
      })

    },
    commentDelete({commit},data){
      const API_URL = `${COMMENT_REST_API}`
      axios({
        url:API_URL,
        method:'DELETE',
        params:data,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }

      }).then(res=>{
        commit
        console.log(res)
      })
    }


  },
  modules: {
  }
})
