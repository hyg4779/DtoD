<template>
  <div class="makestudy">
    <br>
    <div class="container">
      <div class="title">
        스터디 모집
      </div>
      <form @submit="onSubmit">
        <div class="form-group">
          <label for="title">제목</label>
          <br>
          <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
        </div>
        <div class="form-group">
          <div class="stacktitle">기술 스택 및 협업 툴</div>
          <div class="checkbox">
            <div>
              <v-checkbox v-model="stacks.javascript" label="JavaScript"/>
              <v-checkbox v-model="stacks.react" label="React.js"/>
              <v-checkbox v-model="stacks.vue" label="Vue.js"/>
              <v-checkbox v-model="stacks.node" label="Node.js"/>
            </div>
            <div>
              <v-checkbox v-model="stacks.c" label="C"/>
              <v-checkbox v-model="stacks.cpp" label="C++"/>
              <v-checkbox v-model="stacks.cs" label="C#"/>
              <v-checkbox v-model="stacks.typescript" label="TypeScript.js"/>
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
        <div class="detail1 form-group">
          <label for="content1">오픈 카카오톡 (연락처)</label>
          <br>
          <textarea class="form-control" type="text" id="content1" v-model="content1" placeholder=" 연락처를 입력하세요"></textarea>
        </div>
        <div class="detail2 form-group">
          <label for="content2">스터디 소개</label>
          <br>
          <textarea class="form-control" type="text" id="content2" v-model="content2" placeholder=" 스터디 소개를 입력하세요"></textarea>
        </div>
        <div class="submitbtn">
          <button class="cancel" @click="back()">취소</button>
          <button class="submit">등록</button>
        </div>
      </form>
    </div>
    <br>
  </div>
</template>

<script>
import { api } from '../../../api.js'
import axios from 'axios'

export default {
  name: 'MakeStudy',

  data () {
    return {
      title: '',
      content1: '',
      content2: '',
      stacks: {
        javascript: false, c: false, kotlin: false, java: false, 
        react: false, cpp: false, django: false, spring: false, 
        vue: false, cs: false, go: false, flutter: false, 
        node: false, typescript: false, swift: false, etc: false},
      skills: [],
    }
  },
  methods: {
    back () {
      this.$router.replace()
    },
    stacksCheck () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] !== false){
          this.skills.push(property)
        }
      }
    },
    onSubmit(event) {
      event.preventDefault()
      this.stacksCheck()
      if (this.title.length <= 50) {
        axios({
          url: api.CREATE_STUDY_ROOM,
          method: 'POST',
          data: {
            title: this.title,
            content1: this.content1,
            content2: this.content2,
            skills: this.skills,
          },
          headers: {
            Authorization: `JWT ${localStorage.getItem('jwt')}`
          },
        }).then(()=>{
          this.$router.push('/studying')
        }).catch(err=>{
          console.error(err)
        })
      } else {
        alert("제목은 50자 이하로 입력하세요.")
      }  
    },
  },
  created() {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      console.log(token)
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style scoped>

.makestudy {
  background-color: #EBEDF0;
  width: 100%;
  min-height: calc(100vh - 7.498vh);
}

.makestudy .container{
  width: 50vw;
  background-color: white;
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  margin: 0 auto;
  padding: 1vh 2vw;
}

.container .title {
  font-size: 1.6vw;
  font-weight: bold;
  color: #0D1350;
  margin: 2vh 0 0 0;
}

form {
  margin: 2vh 0 0 0;
}

form div label {
  font-weight: bold;
  font-size: 1vw;
  margin: 0 0 1vh 0;
}


form div .stacktitle {
  font-weight: bold;
  font-size: 1vw;
  margin: 0 0 1vh 0;
}

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 30vw;
}

form .detail1 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 45vw;
}

form .detail2 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 45vw;
  margin-bottom: 2vh;
}

form .checkbox {
  margin: 0 0 2vh 0;
  display: grid;
  grid-template-columns: auto auto auto auto;
}

form .checkbox div{
  margin: 0;
}

form .submitbtn {
  margin: 0 0 1vh 0;
}

.submitbtn .cancel {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.85vw;
  font-weight: bold;
  color: #24274A;
  height: 4vh;
  width: 4vw;
  margin: 0 1vw 0 0;
  border: 1px solid;
  background-color: white;
  border-radius: 1.1rem;
}

.submitbtn .submit {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.85vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 4vw;
  margin: 0 1vw 0 0;
  background-color: #24274A;
  border-radius: 1.1rem;
}
</style>
