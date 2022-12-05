import Vue from 'vue'
import VueRouter from 'vue-router'


import HomeView from '../views/HomeView.vue'
import MainView from '@/components/home/MainView.vue'

import UserView from '@/views/UserView.vue'
import ProfileView from '@/components/user/MyProfileView.vue'
import LoginView from '@/components/user/LoginView.vue'
import SignupView from '@/components/user/SignupView.vue'
import FriendView from '@/components/user/FriendView.vue'
import LogoutView from '@/components/user/LogoutView.vue'
import FollowingView from '@/components/user/FollowingView.vue'
import FollowerView from '@/components/user/FollowerView.vue'

import SearchView from '@/views/SearchView.vue'
import SearchResultView from '@/components/search/SearchResultView.vue'
import PlaylistSearchResultView from '@/components/search/PlaylistSearchResultView.vue'

import PlaylistView from '@/views/PlaylistView.vue'
import PlaylistAllView from '@/components/playlist/PlaylistAllView.vue'
import PlaylistCreateView from '@/components/playlist/PlaylistCreateView.vue'
import PlaylistDetailView from '@/components/playlist/PlaylistDatailView.vue'

import VideoView from '@/views/VideoView.vue'
import VideoDetailView from '@/components/video/VideoDetailView.vue'
import LikelistView from '@/components/video/LikelistView.vue'




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home', 
    component: HomeView,
    children: [
      {
        path: '/home',
        name: 'home',
        component: MainView
      }
    ]
  },
  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "",
        name: "login",
        component: LoginView
      },
      {
        path: "/signup",
        name: "signup",
        component: SignupView
      },
      {
        path: "/profile",
        name: "profile",
        component: ProfileView,
        children: [
          {
          path: "",
          name: "friend",
          component: FriendView
        },
        {
          path:"/following",
          name:'following',
          component:FollowingView
        },
        {
          path:"/follower",
          name:'follower',
          component:FollowerView
        }
      ]
      },

      {
        path: "/logout",
        name: 'logout',
        component: LogoutView
      }

    ]
  },
  {
    path: "/search",
    component: SearchView,
    children: [
      {
        path: "",
        name: "search",
        component: SearchResultView
      }
      ,{
        path:"playlist",
        name:"playlistSearch",
        component : PlaylistSearchResultView
      }
    ]
  },
  {
    path: "/playlist",
    component: PlaylistView,
    children: [
      {
        path: "",
        name: "playlist",
        component: PlaylistAllView
      },
      {
        path: "/playlistCreate",
        name: "playlistCreate",
        component: PlaylistCreateView,
      },
      {
        path: "detail",
        name: "playlistDetail",
        component: PlaylistDetailView
      }
    ]
  },
  {
    path:"/video",
    component:VideoView,
    children:[
      {
        path:"",
        name:"likeList",
        component:LikelistView
      },
      {
        path:"detail",
        name:'videoDetail',
        component:VideoDetailView
      }
    ]

  }



]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
