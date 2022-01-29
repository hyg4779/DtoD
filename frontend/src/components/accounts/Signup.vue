<template>
  <b-form class="signform">
    <h1>환영합니다!</h1>
    <div id="box-border">
      <b-form-group class="p-3" id="email" label="이메일" label-for="email" description=" 예시: kimssafy@korea.co.kr">
        <b-form-input
          id="email"
          v-model="email"
          type="email"
          placeholder="사용하실 이메일을 입력해주세요"
          required
        >
        </b-form-input>

      </b-form-group>

      <b-form-group id="password" label="비밀번호" label-for="password" description="영문 대소문자 와 특수문자를 사용해주세요">
        <b-form-input
          id="password"
          v-model="pwd"
          type="password"
          placeholder="사용하실 비밀번호를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="confirm_pwd" label="비밀번호 확인" label-for="confirm_pwd">
        <b-form-input
          id="confirm_pwd"
          v-model="confirm_pwd"
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
  name: 'Signup',
  data () {
    return {
      email: null,
      pwd: null,
      confirm_pwd: null,
      idRules: [
        value => !!value || '필수값입니다!',
        value => (value && value.length >= 6) || '필수값입니다!',
      ],
      pwdRules:[
        value => !!value || '필수값입니다!',
        value => (value && value.length >= 8) || '필수값입니다!',
      ],

    }
  },
  methods:{
    onSubmit () {
      },
    nickNameModalOpen(){
      this.$emit('nickname-modal-open')
    }
  }
}

</script>

<style scoped>
/* #box-border{
  border: 0.15rem solid rgba(155, 155, 155, 0.5);
  border-radius: 0.5rem;
  padding: 1rem 1rem 1.5rem 1rem;
} */

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

#confirm_pwd{
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
