<template>
      <v-container style="max-width: 450px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card>

             

              <div class="pa-10">
                <h1 style="text-align: center" class="mb-10">로그인</h1>
                <form @submit.prevent="login">

                  <v-text-field
                    label="아이디"
                    prepend-inner-icon="mdi-account"
                    v-model="user.userId"
                    required
                  ></v-text-field>

                  <v-text-field
                  label="비밀번호"
                    prepend-inner-icon="mdi-lock"
                    type="password"
                    v-model="user.userPassword"
                  >
                  </v-text-field>

                  <!-- type submit은 $emit("submit") 이라고 생각하면 된다!-->
                  <v-btn
                    type="submit"
                    color="blue lighten-1 text-capitalize"
                    depressed
                    large
                    block
                    dark
                    class="mb-3"
                  >
                    로그인
                  </v-btn>
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
    name : "LoginView",
    data(){
        return{ //연결 잘 됨 
            user:{
                userId:"",
                userPassword:""
            }
        }
    },
    methods:{
        login(){
          if(this.user.userId === "" || this.user.userPassword === ""){
            alert("빈칸이 있습니다. 다시 입력해 주세요");
          }else{
            this.$store.dispatch("login",this.user);
          }
        },
        signup(){
            this.$router.push({name:'signup'})
        }
    },
    created(){
        
        
        if(this.$store.state.loginUser != null){
          this.$router.push({name:'home'})
        }

    }
}
</script>

<style>

</style>