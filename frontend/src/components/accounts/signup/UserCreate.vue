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
import {ref} from 'vue'

export default {
  emits: ['user-create-fin'],
  
  setup(props, {emit}){
    const signup_email = ref(null)
    const pwd = ref(null)
    const confirm_pwd = ref(null)

    function onSubmit () {
      if (signup_email.value === true ||
        pwd.value === true && pwd.value.length > 9 ||
        confirm_pwd.value === pwd.value) {
        console.log(props.user)
        alert('Success')
        emit('user-create-fin', {signup_email, pwd, confirm_pwd})            
        }
      }

    return {
      signup_email,
      pwd,
      confirm_pwd,
      onSubmit,

      onReset () {
        signup_email.value = null
        pwd.value = null
        confirm_pwd.value = null
      },
    }
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