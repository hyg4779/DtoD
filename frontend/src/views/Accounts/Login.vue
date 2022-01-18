<template>
    <!-- <q-dialog v-model="icon"> -->
    <q-card v-model="icon">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6">로그인</div>
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="loginform">
        <q-form
          @submit="onSubmit"
          @reset="onReset"
          class="q-gutter-md loginform"
        >
          <h3>환영합니다!</h3>
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
    </q-card>
    <!-- </q-dialog> -->
</template>

<script>
import { useQuasar } from 'quasar'
import { ref } from 'vue'

export default {
  setup () {
    const $q = useQuasar()

    const user_id = ref(null)
    const pwd = ref(null)
    const accept = ref(false)

    return {
      user_id,
      pwd,
      accept,
      icon: ref(false),
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

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
      },
      iconTrue(){
        this.icon.value = true
      },
    }
  }
}

</script>

<style>
.loginform{
  margin: 2rem;
}
</style>