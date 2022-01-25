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
            @user-create-fin="page2On"
           />
          <Nickname
            :class="{signupform: true, visible: page4 ,invisible: !page2,}"
            v-if="page2 === true && page3 === false"
            @nickname-fin="page3On"
          />
          <Jobs
            :class="{signupform: true, visible: page2 ,invisible: !page3,}"
            v-if="page3 === true && page4 === false"
            @jobs-fin="page4On"
          />
          <Skills
            :class="{signupform: true, visible: page3 ,invisible: !page4,}"
            v-if="page4 === true"
            @stacks-fin="makeUser"
          />
      </q-card>
    </q-dialog>
</template>

<script>
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

  data () {
    return {
      page1: false,
      page2: false,
      page3: false,
      page4: false,
    }
  },
  methods:{
    page2On (payload) {
      // signup_email, pwd 받아오고, nickname.vue 로 전환
      this.$store.dispatch('userCreate', payload)

      this.page1 = false
      setTimeout(() => {
        this.page2 = true
      }, 300)},

    // 닉네임 받아오고 jobs.vue로 전환
    page3On (payload) {
      this.$store.dispatch('nickName', payload)
     
      this.page2 = false
      setTimeout(() => {
        this.page3 = true
      }, 300)},

    // 직무 받아오고 skills.vue로 전환
    page4On (payload) {
      this.$store.dispatch('jobs', payload)

      this.page3 = false
      setTimeout(() => {
        this.page4 = true
      }, 300)},


    // 기술스텍까지 완료하고 user 만들기
    makeUser(payload){
      this.$store.dispatch('stacks', payload)
      alert('축하합니다! 회원가입에 성공했습니다')
      alert('로그인을 진행해주세요!')
      this.$emit('go-login')
    },
  }
}

</script>

<style scoped>

.signupmodal {
  height: 30rem;
  width: 100%;
  margin: 0 auto;
  border-radius: 20px !important;
  box-shadow: 5px 5px 5px rgb(44, 44, 44) !important;
}

.signupmodal h3{
  font-family: 'Hanna', sans-serif;
  text-align: center;
  margin: 0 0 40px 0;
}

.signupform {
  margin: auto 20px !important;
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