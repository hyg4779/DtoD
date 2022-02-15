<template>
  <b-form class="signform">
    <h1>환영합니다!</h1>
    <div id="box-border">
      <div class="email">
        <b-form-group class="p-3" id="email" label="이메일" label-for="email" description=" 예시: kimssafy@korea.co.kr">
          <b-form-input
            id="email"
            v-model="credentials.email"
            type="email"
            placeholder="사용하실 이메일을 입력해주세요"
            required
          >
          </b-form-input>
        </b-form-group>
        <div class="doublecheck">
          <input type="button" @click="doubleCheck" value="중복체크" />
        </div>
      </div>
      <b-form-group id="password" label="비밀번호" label-for="password" description="영문 대소문자 와 특수문자를 사용해주세요">
        <b-form-input
          id="password"
          v-model="credentials.pwd"
          type="password"
          placeholder="사용하실 비밀번호를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="confirm_password" label="비밀번호 확인" label-for="confirm_password">
        <b-form-input
          id="confirm_password"
          v-model="credentials.confirm_pwd"
          type="password"
          placeholder="한번 더 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <div id="btn_group">
        <b-button id="sumbit_btn" pill @click="nickNameModalOpen">다음</b-button>
      </div>
    </div>
  </b-form>
    
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'

export default {
  name: 'SignUp',
  data () {
    return {
      credentials: {
        email: null,
        pwd: null,
        confirm_pwd: null,
      },
      emailCheck: null,
    }
  },
  methods:{
    doubleCheck () {
      // event.preventDefault()
      const token = localStorage.getItem('jwt')
      if (this.credentials.email !== null) {
        axios({
          url: api.EMAIL_CHECK + `${this.credentials.email}`,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          // console.log(res)
          if (res.data === true) {
            this.emailCheck = res.data
            console.log(this.emailCheck)
            alert('중복되는 이메일이 있습니다.')
          }
          else {
            this.emailCheck = res.data
            console.log(this.emailCheck)
            alert('중복되는 이메일이 없습니다.')
          }
        }).catch(err=>{
          console.log(err)
        })     
      }
      else {
        alert("사용하실 이메일을 입력하세요")
      }
    },

    verifyEmail (){
      // email형식
      let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

      // 빈값이면 오류나기 match함수로 오류나기 전에 false처리
      if (this.credentials.email === null){
        return false
      }

      let data = this.credentials.email.match(regExp)
      // console.log(data)
      if(data === null){  // 정규식 통과하면 true
        return false
      }else{

        return true
      }
    },

    verifyPwd(){
      // 문자 1개, 숫자1개, 특수문자1개, 8자-12자
      let regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

      // 빈값이면 오류나기 match함수로 오류나기 전에 false처리
      if (this.credentials.pwd === null){
        return false
      }

      let data = this.credentials.pwd.match(regExp)
      // console.log(data)
      if(data === null){  // 정규식 통과하면 true
        return false
      }else{

        return true
      }
    },
    
    nickNameModalOpen(){
      if (this.emailCheck !== null && this.emailCheck === false) {
        let email_result = this.verifyEmail()
        let pwd_result = this.verifyPwd()
        // 1차확인: 이메일, 비밀번호 형식 확인
        if (email_result && pwd_result){
          // 2차확인: 비밀번호, 비밀번호확인 일치 확인  =>  닉네임 모달 open
          if(this.credentials.pwd === this.credentials.confirm_pwd){
            this.$store.dispatch('userCreate', this.credentials)
            return this.$emit('nickname-modal-open')
  
          }else{  // 비밀번호 확인 불일치
            this.$swal({
            icon: 'error',
            titleText: '비밀번호가 일치하지 않습니다',
            showConfirmButton: false,
            timer: 1500,
            })
            this.credentials.confirm_pwd = null
            this.emailCheck = null
          }
        }else{  // 이메일, 비밀번호 형식 미충족
          this.$swal({
            icon: 'error',
            titleText: `이메일 또는 비밀번호     
            형식을 맞춰주세요`,
            showConfirmButton: false,
            timer: 1500,
          })
          this.credentials.email = null
          this.credentials.pwd = null
          this.credentials.confirm_pwd = null
          this.emailCheck = null
        }
      }
      else {
        this.emailCheck = null
        alert('이메일 중복검사를 해주세요')
        return
      }
    },
  }
}

</script>

<style scoped>
.signform{
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
  min-height: 600px !important;
}
.signform h1{
  margin: 1rem;
  text-align: center;
  /* font-weight: bold; */
  font-family: 'Dohyeon', sans-serif;
}

.signform input{
  margin-top: 0.4rem;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}
.email {
  display: flex;
}
.email .doublecheck input{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  text-align: center;
  border: 1px solid;
  color: #24274A;
  height: 4vh;
  width: 5vw;
  margin: 7vh 0 0 1.56vw;
  background-color: white;
  border-radius: 0.8rem;
  box-shadow: none !important;
}
#email{
  font-weight:bold !important;
  font-size: 1vw;
  padding: 1rem; 
  width: 17vw;
}

#password{
  font-weight:bold !important;
  font-size: 1vw;
  padding: 1rem; 
}

#confirm_password{
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
}

/* #check-btn{
  margin-left: 16px !important;
  margin-right: auto !important;
} */


#btn_group{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#sumbit_btn{
  background-color: rgb(50, 50, 255) !important;
  font-weight:bold !important;
  border: none;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
  max-width: 6rem;
  margin: 0.5rem 0 0 0;
  padding: 0.5rem 1rem 0.5rem 1rem;

}

#sumbit_btn:hover{
  background-color: rgb(75, 75, 255) !important;
}
</style>
