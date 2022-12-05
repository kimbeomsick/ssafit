<template>
  <v-container style="max-width: 450px" fill-height>
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-card>
          <div class="pa-10">
            <h1 style="text-align: center" class="mb-10">회원가입</h1>
            <form>
              <!-- 이름,닉네임,제일좋아하는 운동, -->
              <v-text-field
                label="아이디"
                prepend-inner-icon="mdi-account"
                v-model.trim="user.userId"
              ></v-text-field>

              <v-text-field
              label="비밀번호"
                prepend-inner-icon="mdi-lock"
                type="password"
                v-model.trim="user.userPassword"
              >
              </v-text-field>

              <v-text-field
              label="비밀번호확인"
                prepend-inner-icon="mdi-lock"
                type="password"
                v-model.trim="userPasswordConfirm"
                
              >
              </v-text-field>

              <v-text-field
              label="이름"
                prepend-inner-icon="mdi-account"
                type="text"
                v-model.trim="user.name"
              >
              </v-text-field>

              <v-text-field
              label="닉네임"
                prepend-inner-icon="mdi-account"
                type="text"
                v-model.trim="user.nickname"
              >
              </v-text-field>

              <v-text-field
              label="관심있는 운동"
                prepend-inner-icon="mdi-arm-flex"
                type="text"
                v-model.trim="user.favorite"
                placeholder="수영, 필라테스, 헬스 등.."
                
              >
              </v-text-field>
             
              <v-btn
                @click="signup"
                color="blue lighten-1 text-capitalize"
                depressed
                large
                block
                dark
              >
                회원가입
              </v-btn>
            </form>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name : 'SignupView',
  data(){
    return {
      userPasswordConfirm:"",
      user:{
        userId:"",
        userPassword:"",
        name:"",
        nickname:"",
        favorite:""
      }
    }
  },
  methods:{
    signup(){
      if(this.user.name && this.user.nickname && this.user.userPassword && this.user.userId && this.user.favorite){

        if(this.user.userPassword === this.userPasswordConfirm){
          this.$store.dispatch("signup",this.user)
        }else{
          alert("비밀번호가 다릅니다. 다시 입력해 주세요")
        }
      }else{
        alert("모든 칸을 다 채워주세요 ")
      }
    },
  }
  
}
</script>

<style>

</style>