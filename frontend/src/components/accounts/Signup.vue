<template>
  <q-dialog>
    <q-card class="signupmodal">
      <q-card-section class="row items-center q-pb-none">
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>
      <q-carousel
      class="carousel"
      animated
      v-model="slide"
      navigation
      :autoplay="14400"
      arrows
      control-color="primary"
      transition-prev="slide-right"
      transition-next="slide-left"
      @mouseenter="autoplay = false"
      @mouseleave="autoplay = false"
      >
        <q-carousel-slide :name="1" class="slide">
          <h3>어서오세요!</h3>
          <div class="signupform">
            <q-form
              @submit="onSubmit"
              @reset="onReset"
              class="q-gutter-md signupform"
            >
              <q-input
                filled
                v-model="singup_email"
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
                  val => val > 0 && val < 100 || '필수 값입니다'
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
                  val => val !== null && val !== '' || '필수 값입니다.',
                  val => val > 0 && val < 100 || '필수 값입니다.'
                ]"
              />
              <q-input
                filled
                v-model="nick_name"
                label="닉네임 *"
                lazy-rules
                hint="자유롭게 사용하세요"
                
                :rules="[ val => val && val.length > 0 || '필수 값입니다']"
              />
              <!-- <div class="signupbtn">
                <q-btn label="다음" type="submit" color="primary"/>
                <q-btn label="Reset" type="reset" color="primary" flat />
              </div> -->
            </q-form>
          </div>
        </q-carousel-slide>
        <q-carousel-slide :name="2" class="slide">
          <h3>어서오세요!</h3>
          <div class="signupform">
            <q-form
              @submit="onSubmit"
              @reset="onReset"
              class="q-gutter-md signupform"
            >
              <q-input
                filled
                v-model="singup_email"
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
                  val => val > 0 && val < 100 || '필수 값입니다'
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
                  val => val !== null && val !== '' || '필수 값입니다.',
                  val => val > 0 && val < 100 || '필수 값입니다.'
                ]"
              />
              <q-input
                filled
                v-model="nick_name"
                label="닉네임 *"
                lazy-rules
                hint="자유롭게 사용하세요"
                
                :rules="[ val => val && val.length > 0 || '필수 값입니다']"
              />
              <!-- <div class="signupbtn">
                <q-btn label="다음" type="submit" color="primary"/>
                <q-btn label="Reset" type="reset" color="primary" flat />
              </div> -->
            </q-form>
          </div>
        </q-carousel-slide>
      </q-carousel>
    </q-card>
  </q-dialog>
</template>

<script>
import { useQuasar } from 'quasar'
import { ref } from 'vue'


export default {
  name: 'Signup',

  setup () {
    const $q = useQuasar()

    const singup_email = ref(null)
    const pwd = ref(null)
    const accept = ref(false)
    const confirm_pwd = ref(null)
    const nick_name = ref(null)

    return {
      singup_email,
      pwd,
      accept,
      confirm_pwd,
      nick_name,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

      slide: ref(1),
      autoplay: ref(false),
      
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
        singup_email.value = null
        pwd.value = null
        confirm_pwd.value = null
        nick_name.value = null
        accept.value = false
      },
    }
  }
}

</script>

<style scoped>

.carousel {
  margin: 0;
  padding: 0 !important;
  height: 60vh;
}

.slide {
  width: 100%;
}

.signupmodal {
  height: 70vh;
  width: 70vw;
  border-radius: 20px !important;
  box-shadow: 5px 5px 5px rgb(44, 44, 44) !important;
}

.signupmodal h3{
  font-family: 'Hanna', sans-serif;
  text-align: center;
  margin: 0 0 20px 0;
}

.signupform {
  height: 1rem;
  margin: 0 20px 0 20px !important;
}


.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}

.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}
</style>