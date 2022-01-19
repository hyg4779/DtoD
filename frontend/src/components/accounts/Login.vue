<template>
  <!-- 로그인 모달 -->
  <q-dialog>
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
          </div>
          <div class="signupbtn">
            <q-btn
              label="아직 계정이 없으신가요?" style="color: #3988D7" flat
              @click="$emit('on-signal')"
            />
          </div>
        </q-form>
      </div>
    </q-card>
  </q-dialog>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref } from 'vue'


export default {
  name: 'Login',
  emits: ['on-signal'],
  setup () {
    const $q = useQuasar()

    const user_email = ref(null)
    const pwd = ref(null)
    const accept = ref(false)
    return {
      user_email,
      pwd,
      accept,
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
        user_email.value = null
        pwd.value = null
        accept.value = false
      },
      // onSignal() {
      //   console.log('1')
      //   this.$emit('on-signal')
      // }
    }
  }
}

</script>

<style>

.loginmodal {
  height: 70vh;
  width: 70vw;
  border-radius: 20px !important;
  box-shadow: 5px 5px 5px rgb(44, 44, 44) !important;
}

.loginmodal h3{
  font-family: 'Hanna', sans-serif;
  text-align: center;
}

.loginform {
  margin: 50px 20px 0 20px !important;
}


.loginform .loginbtn {
  margin: 20px;
  text-align: center;
}

.loginform .signupbtn {
  margin: 20px;
  text-align: center;
}

</style>
