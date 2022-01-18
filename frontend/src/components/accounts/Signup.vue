<template>
  <div class="signupform">
    <h3>어서오세요!</h3>
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="user_id"
        label="아이디 *"
        lazy-rules
        hint="영문 숫자 조합으로 만드세요"
        
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />
      <q-input
        filled
        type="password"
        v-model="pwd"
        label="비밀번호 *"
        lazy-rules
        hint="영문 숫자 특수문자 조합으로 만드세요"

        :rules="[
          val => val !== null && val !== '' || 'Please type your age',
          val => val > 0 && val < 100 || 'Please type a real age'
        ]"
      />
      <q-input
        filled
        type="password"
        v-model="pwd"
        label="비밀번호 확인 *"
        lazy-rules
        hint="다시 한 번 입력하세요"
        :rules="[
          val => val !== null && val !== '' || 'Please type your age',
          val => val > 0 && val < 100 || 'Please type a real age'
        ]"
      />
      <q-input
        filled
        v-model="nick_name"
        label="닉네임 *"
        lazy-rules
        hint="자유롭게 사용하세요"
        
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />
      <q-toggle v-model="accept" label="I accept the license and terms" />
      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
        
      </div>
    </q-form>
  </div>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref } from 'vue'

export default {
  name: 'Signup',
  
  setup () {
    const $q = useQuasar()

    const name = ref(null)
    const age = ref(null)
    const accept = ref(false)
    const nick_name = ref(null)

    return {
      name,
      age,
      accept,
      nick_name,

      onSubmit () {
        if (accept.value !== true) {
          $q.notify({
            color: 'red-5',
            textColor: 'white',
            icon: 'warning',
            message: 'You need to accept the license and terms first'
          })
        }
        else {
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Submitted'
          })
        }
      },

      onReset () {
        name.value = null
        age.value = null
        accept.value = false
      }
    }
  }
}

</script>

<style>
.signupform{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 40rem;
  margin: auto;
  margin-top: 5rem;
  padding: 2rem;
  border: .3rem rgb(190, 190, 190) solid ;
  border-radius: 1rem;
}
</style>