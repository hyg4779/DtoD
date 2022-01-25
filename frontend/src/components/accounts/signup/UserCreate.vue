<template>
   <div 
    class="signupform"
    >
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="signup_email"
        label="이메일 *"
        lazy-rules
        hint="ex) kimssafy@korea.co.kr"
  
        :rules="[ val => val && val.length > 0 || '이메일을 입력하세요']"
      />
      <q-input
        filled
        type="password"
        v-model="pwd"
        label="비밀번호 *"
        lazy-rules
        hint="영문 숫자 특수문자 조합으로 만드세요"

        :rules="[
          val => val !== null && val !== '' || '필수 값입니다',
          val => val.length > 9 || '너무 짧습니다'
        ]"
      />
      <q-input
        filled
        type="password"
        v-model="confirm_pwd"
        label="비밀번호 확인 *"
        lazy-rules
        hint="다시 한 번 입력하세요"
        :rules="[
          val => val === pwd || '일치하지 않습니다',
        ]"
      />
      <div class="signupbtn">
        <q-btn label="다음" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat />
      </div>
    </q-form>
  </div>
</template>

<script>
export default {
  name: 'UserCreate',
  props:{
    user:{
      type: Object,
    }
  },
  data(){

    return {
      signup_email: null,
      pwd : null,
      confirm_pwd: null,
    }
  },
  methods:{
    onSubmit () {
      // email, pwd 정규표현식
      // let emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      // let pwdRegExp = /^([!@#$%^&*]?[0-9a-zA-Z]){4,12}$/


      if (this.signup_email === true ||
        this.pwd === true && this.pwd.length > 9 ||
        this.confirm_pwd === this.pwd) {
          console.log(this.user)
        alert('Success')
        let email = this.signup_email
        let password = this.pwd
        this.$emit('user-create-fin', {email, password})            
        }
      },
      onReset () {
        this.signup_email = null
        this.pwd = null
        this.confirm_pwd = null
      },

  }


}
</script>

<style>
.signupform {
  margin: 50px 20px 0 20px !important;
}

.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}
</style>