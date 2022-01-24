<template>
    <q-dialog>
      <q-card class="signupmodal">
        <q-card-section class="row items-center q-pb-none">
          <q-space />
          <q-btn icon="close" flat round dense v-close-popup />
        </q-card-section>
        <h3>어서오세요!</h3>
          <Usercreate
            :class="{signupform: true, visible: page1 ,invisible: !page1,}"
            v-if="page1 === true && page2 === false"
            :user="user"
            @user-create-fin="page2On"
           />
          <Nickname
            :class="{signupform: true, visible: page4 ,invisible: !page2,}"
            v-if="page2 === true && page3 === false"
            :user= user
            @nickname-fin="page3On"
          />
          <Jobs
            :class="{signupform: true, visible: page2 ,invisible: !page3,}"
            v-if="page3 === true && page4 === false"
            :user = user
            @jobs-fin="page4On"
          />
          <Skills
            :class="{signupform: true, visible: page3 ,invisible: !page4,}"
            v-if="page4 === true"
            :user = user
            @stacks-fin="makeUser"
          />
      </q-card>
    </q-dialog>
</template>

<script>
// import { useQuasar } from 'quasar'
import { ref,reactive } from 'vue'
import Jobs from './signup/Jobs.vue'
import Skills from './signup/Skills.vue'
import Usercreate from './signup/UserCreate.vue'
import Nickname from './signup/Nickname.vue'

export default {
  components:{
    Usercreate,
    Jobs,
    Skills,
    Nickname
  },
  emits:['go-login'],

  setup ( props, {emit} ) {
    
    // const $q = useQuasar()
    const user = reactive({
      signup_email:'',
      pwd: '',
      confirm_pwd: '',
      nick_name: '',
      jobs: '',
      stacks: null,
    })
    const nick_name = ref(null)
    const page1 = ref(true)
    const page2 = ref(false)
    const page3 = ref(false)
    const page4 = ref(false)

    return {
      user,
      nick_name,
      page1,
      page2,
      page3,
      page4,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),
      
      page2On (payload) {
        // emain, pwd, confirm_pwd 받아오고, nickname.vue 로 전환
        user.signup_email = payload.signup_email.value
        user.pwd = payload.pwd.value
        user.confirm_pwd = payload.confirm_pwd.value

        page1.value = false
        setTimeout(() => {
          page2.value = true
        }, 300)},

      // 닉네임 받아오고 jobs.vue로 전환
      page3On (payload) {
        user.nick_name = payload
              
        page2.value = false
        setTimeout(() => {
          page3.value = true
        }, 300)},

      // 직무 받아오고 skills.vue로 전환
      page4On (payload) {
        user.jobs = payload
        page3.value = false
        
        setTimeout(() => {
          page4.value = true
        }, 300)},


      // 기술스텍까지 완료하고 user 만들기
      makeUser(payload){
        user.stacks = payload
        console.log(user)
        alert('축하합니다! 회원가입에 성공했습니다')
        alert('로그인을 진행해주세요!')
        emit('go-login')
      },
      onReset(){
        for(let property in user){
          user[property] = null
        }
      },

          // $q.notify({
          //   color: 'red-5',
          //   textColor: 'white',
          //   icon: 'warning',
          //   message: 'You need to accept the license and terms first'
          // })
        // else {
          // $q.notify({
          //   color: 'green-4',
          //   textColor: 'white',
          //   icon: 'cloud_done',
          //   message: 'Submitted'
          // })
        // }
    }
  }
}

</script>

<style>

.signupmodal {
  height: 70vh;
  width: 70vw;
  border-radius: 20px !important;
  box-shadow: 5px 5px 5px rgb(44, 44, 44) !important;
}

.signupmodal h3{
  font-family: 'Hanna', sans-serif;
  text-align: center;
}

.signupform {
  margin: 50px 20px 0 20px !important;
}


.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}

.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}

/* fadeout */
.invisible{
  overflow: hidden;
  opacity: 0;
  transition: 0.5s linear;
}

/* fadein */
.visible {
  overflow: visible;
  opacity: 1;
  transition: opacity 0.5s linear;
}

</style>