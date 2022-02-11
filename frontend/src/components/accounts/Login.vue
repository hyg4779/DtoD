<template>
  <div>
    <b-form class="loginform">
      <h1>안녕하세요!</h1>
      <b-form-group id="email" label="이메일" label-for="email">
        <b-form-input
          id="email"
          v-model="credentials.userEmail"
          type="email"
          placeholder="이메일을 입력하세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="password" label="비밀번호" label-for="password">
        <b-form-input
          id="password"
          v-model="credentials.userPwd"
          type="password"
          placeholder="비밀번호를 입력하세요"
          required
          @keypress.enter="Login"
        ></b-form-input>
      </b-form-group>

      <div id="btn_group">
        <b-button id="login_btn" pill @click="Login">로그인</b-button>
      </div>
      
    </b-form>
    <b-button id="sign_btn" pill><a @click="signupModalOpen">아직 계정이 없으신가요?</a></b-button>

  </div>
    
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'

export default {
  name: 'Login',
  data () {
    return {
      credentials: {
        userEmail: null,
        userPwd: null,
      },
    }
  },

  methods:{

    // 정규표현식 메서드
    verifyEmail (){
      let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      let data = this.credentials.userEmail.match(regExp)
      // console.log(data)
      if(data !== null || this.credentials.userEmail === 'admin'){
        return true
      }return false
    },

    // 로그인
    Login(){
      if(this.verifyEmail()){
         axios({
          method: 'post',
          url: api.LOG_IN,
          data: this.credentials,
        })
        .then(res => {
          localStorage.setItem('jwt', res.data.token)
          this.$emit('Login')
        })
        .catch(err => {
          console.log(err)
          this.$swal({
          toast: true,
          width: "17rem",
          icon: 'error',
          titleText: '다시 확인해주세요',
          showConfirmButton: false,
          timer: 1500,
        })
        })
      } else {
        this.$swal({
          toast: true,
          width: "21rem",
          icon: 'error',
          titleText: '이메일 형식을 맞춰주세요',
          showConfirmButton: false,
          timer: 1500,
        })
      }
    },

    // 회원가입 모달 열기
    signupModalOpen(){
      this.$emit('signup-modal-open')
    },
  }
}

</script>

<style scoped>
.loginform{
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
}
.loginform h1{
  margin: 1rem;
  text-align: center;
  /* font-weight: bold; */
  font-family: 'Dohyeon', sans-serif;
}

.loginform input{
  margin-top: 0.4rem;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;

}

#email{
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
}

#password{
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
}

#btn_group{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#login_btn{
  background-color: rgb(50, 50, 255) !important;
  font-weight:bold !important;
  border: none;
  max-width: 6rem;
  margin: 0.5rem 0 0 0;
  padding: 0.5rem 1rem 0.5rem 1rem;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

#login_btn:hover{
  background-color: rgb(75, 75, 255) !important;
}

#sign_btn:hover{
  border: none !important; 
  box-shadow: none !important;
  color: rgb(100, 100, 255) !important;
}

#sign_btn{
  color: rgb(75, 75, 212);
  background-color: white !important;
  border: none;
  /* font-weight:bold !important; */
  max-width: 15rem;
  margin: auto 8rem;
  /* padding: 0.5rem 1rem 0.5rem 1rem; */
  border-radius: 1rem !important;
}

</style>
