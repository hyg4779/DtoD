<template>
  <v-dialog
    persistent
    max-width="600px"
  >
    <v-card>
      <v-card-title>
        <span class="text-h5">회원가입</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="이메일*"
                v-model="email"
                required
                :rules="idRules"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="pwd"
                label="비밀번호*"
                type="password"
                :rules="pwdRules"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="confirm_pwd"
                label="비밀번호확인*"
                type="password"
                :rules="confirm_pwdRules"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <br>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="blue darken-1"
          text
          @click="onReset"
        >
          RESET
        </v-btn>
        <v-btn
          color="blue darken-1"
          text
          @click="this.$emit('signup-close')"
        >
          CLOSE
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      dialog: false,
      email: null,
      pwd: null,
      confirm_pwd: null,
      idRules: [
        value => !!value || '필수값입니다!',
        value => (value && value.length >= 6) || '너무짧습니다!',
      ],
      pwdRules: [
        value => !!value || '필수값입니다!',
        value => (value && value.length >= 8) || '너무짧습니다!',
      ],
      confirm_pwdRules:[
        value => value === this.pwd || '일치하지 않습니다!',
        ],
    }
  },
  methods:{
    onSubmit () {
    },

    onReset(){
      this.email = null
      this.pwd = null
      this.confirm_pwd = null
    },
    singupModalOpen(){
      this.$emit('singup-signal')
    },
  }
}

</script>

<style scoped>

.loginmodal {
  height: auto;
  width: 100%;
  margin: 0 auto;
  border-radius: 20px !important;
  box-shadow: 5px 5px 5px rgb(44, 44, 44) !important;
}

.loginmodal h3{
  font-family: 'Hanna', sans-serif;
  text-align: center;
}

.loginform {
  margin: auto 20px !important;
}

.loginform .loginemail {
  margin: 0 40px 40px 40px;
}

.loginform .loginpw {
  margin: 0 40px 0 40px;
}

.loginform .loginbtn {
  margin: 70px;
  text-align: center;
}

/* .loginform .signupbtn {
  margin: 20px;
  text-align: center;
} */

</style>
