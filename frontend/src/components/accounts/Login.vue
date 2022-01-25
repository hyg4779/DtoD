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
          <span class="text-h5">로그인</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Email*"
                  required
                  hint="예시) kimssafy@korea.co.kr"

                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Password*"
                  type="password"
                  hint=" 영문 대소문자, 숫자, 특수문자 조합으로 입력하세요"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            로그인
          </v-btn>
          <br>
          <v-btn
            color="blue darken-1"
            text
            @click="this.$emit('signup-modal-open')"
          >
            아직 계정이 없으신가요?
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  <!-- 로그인 모달 -->
  <!-- <q-dialog>
    <q-card class="loginmodal">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <h3>환영합니다!</h3>
      <div class="loginform">
        <q-form
          @submit="onSubmit"
          @reset="onReset"
          class="q-gutter-md loginform"
        >
          <q-input
            class="loginemail"
            filled
            v-model="user_email"
            label="이메일 *"
            lazy-rules
            :rules="[ val => val && val.length > 0 || '이메일을 입력하세요']"
          />
          <q-input
            class="loginpw"
            filled
            type="password"
            v-model="pwd"
            label="비밀번호 *"
            lazy-rules
            :rules="[
              val => val !== null && val !== '' || '비밀번호를 입력하세요',
              val => val > 0 && val < 100 || '비밀번호를 입력하세요'
            ]"
          />
          <div class="loginbtn">
            <q-btn label="로그인" type="submit" color="primary"/>
            <q-btn label="Reset" type="reset" color="primary" flat />
            <q-space/><br/>
            <q-btn
              label="아직 계정이 없으신가요?" style="color: #3988D7" flat
              @click="$emit('on-signal')"
            />
          </div>
        </q-form>
      </div>
    </q-card>
  </q-dialog> -->
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      dialog: false,
      user_email: null,
      pwd: null,
    }
  },
  methods:{
    onSubmit () {
    },

    onReset () {
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
