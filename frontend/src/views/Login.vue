<template>
  <div class="loginform">
    <h2>환영합니다!</h2>
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
        :rules="[ val => val && val.length > 0 || '아이디를 입력하세요']"
      />
      <q-input
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
      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
        <q-btn label="Signup" color="primary"
          flat class="q-ml-sm"
          @click="$router.push({name:'Signup'})"
         />
        
      </div>
    </q-form>
  </div>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref } from 'vue'

export default {
  name: 'Login',
  setup () {
    const $q = useQuasar()

    const user_id = ref(null)
    const pwd = ref(null)
    const accept = ref(false)

    return {
      user_id,
      pwd,
      accept,

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
        user_id.value = null
        pwd.value = null
        accept.value = false
      }
    }
  }
}

</script>

<style>
.loginform{
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