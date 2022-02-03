<template>
  <b-form class="signform">
    <h1>환영합니다!</h1>
    <div id="box-border">
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
export default {
  name: 'SignUp',
  data () {
    return {
      credentials: {
        email: null,
        pwd: null,
        confirm_pwd: null,
      },    
    }
  },
  methods:{
    verifyEmail (){
      // email형식
      let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if(this.credentials.email.match(regExp) !== null){
        return true
      }return false
    },
    verifyPwd(){
      // 영문 대문자 1개, 소문자 1개, 숫자1개, 특수문자1개, 8자-12자
      let regExp = /(([^ ])[a-z]+[A-Z]+[0-9]+[!@#$%^&*]+){8,12}/;

      if(this.credentials.pwd.match(regExp) !== null){
        return true
      }return false
    },
    nickNameModalOpen(){
      let email_result = this.verifyEmail()
      let pwd_result = true
      if ((email_result && pwd_result) &&
        (this.credentials.pwd === this.credentials.confirm_pwd)){
        this.$store.dispatch('userCreate', this.credentials)
        return this.$emit('nickname-modal-open')
      }return alert('다시 확인해주세요!')
      
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
