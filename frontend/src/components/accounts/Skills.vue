<template>
  <b-form class="skillsform">
    <h1>환영합니다!</h1>

      <div class="mt-2">
        <label for="checkbox">사용하는 기술들을 알려주세요</label>
        <div class="checkbox" id="checkbox">
          <div>
            <v-checkbox v-model="stacks.javascript" label="JavaScript"/>
            <v-checkbox v-model="stacks.react" label="React"/>
            <v-checkbox v-model="stacks.vue" label="Vue"/>
            <v-checkbox v-model="stacks.node" label="Node"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.c" label="C"/>
            <v-checkbox v-model="stacks.cpp" label="C++"/>
            <v-checkbox v-model="stacks.python" label="python"/>
            <v-checkbox v-model="stacks.typescript" label="TypeScript"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.kotlin" label="Kotlin"/>
            <v-checkbox v-model="stacks.django" label="Django"/>
            <v-checkbox v-model="stacks.go" label="Go"/>
            <v-checkbox v-model="stacks.swift" label="Swift"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.java" label="Java"/>
            <v-checkbox v-model="stacks.spring" label="Spring"/>
            <v-checkbox v-model="stacks.flutter" label="Flutter"/>
            <v-checkbox v-model="stacks.etc" label="Etc"/>
          </div>
        </div>
      </div>
    <div id="btn_group">
      <b-button id="sumbit_btn" pill @click="signupFin">완료</b-button>
    </div>
    
  </b-form>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'

export default {

  data () {
    return {
      stacks: {
        javascript: false,
        c: false,
        kotlin: false,
        java: false, 
        react: false,
        cpp: false,
        django: false,
        spring: false, 
        vue: false,
        python: false,
        go: false,
        flutter: false, 
        node: false,
        typescript: false,
        swift: false,
        etc: false
      },
      credentials: {
        skills: [],
      },
    }
  },

  methods:{
    // 체크한 기술스텍만 배열에 담아 signup으로 보내기
    stacksCheck () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] !== false){
          this.credentials.skills.push(property)
          }
        }
      },
    signupFin(){
      this.stacksCheck()
      // console.log(this.credentials.skills)
      if(this.credentials.skills.length >= 1){
        this.$store.dispatch('skills', this.credentials)
        axios({
          method: 'post',
          url: api.SIGN_UP,
          data: this.$store.state.credentials
        })
        .then(() => {
          // console.log(res)
          this.$emit('signup-fin')  
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        this.$swal({
          icon: 'error',
          titleText: '1개 이상 입력해주세요',
          showConfirmButton: false,
          timer: 1500,
        })
        this.credentials.skills = []
        this.$store.dispatch('skills', this.credentials)
      }
    }
  },

}

</script>

<style scoped>
.skillsform{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
  min-height: 600px !important;
}
.skillsform h1{
  margin: 1rem;
  text-align: center;
  /* font-weight: bold; */
  font-family: 'Dohyeon', sans-serif;
}

.skillsform label{
  font-weight: bold;
  font-size: 1.2rem;
  color: grey;
  margin-bottom: 0.5rem;
}

.checkbox {
  display: grid;
  grid-template-columns: 25% 25% 25% 25%;
}

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
  margin-top: 3rem;
  padding: 0.5rem 1rem 0.5rem 1rem;

}

#sumbit_btn:hover{
  background-color: rgb(75, 75, 255) !important;
}
</style>