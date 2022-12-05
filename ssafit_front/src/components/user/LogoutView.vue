<template>
  <v-container>
    <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          LOGOUT
        </v-card-title>

        <v-card-text>
           <v-spacer></v-spacer> 
          정말 로그아웃 하시겠습니까?
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="logout"
          >
            logout
          </v-btn>
          <v-btn
            color="danger"
            text
            @click="cancel"
          >
            cancel
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
  </v-container>
</template>

<script>


export default {
    data(){
        return{
            dialog:true
        }
    },
    methods:{
        cancel(){
            this.$router.go(-1)
        },
        logout(){
            this.$store.dispatch("logout")          
        }
    }
}
</script>

<style>

</style>