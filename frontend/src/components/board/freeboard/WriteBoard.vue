<template>
  <div class="writeboard">
    <div class="title">
      게시글 작성
    </div>
    <form @submit="onSubmit">
      <div class="form-group">
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="form-group">
        <div class="categorytitle">카테고리</div>
        <div class="categorybox">
          <input type="radio" name="radioBtn" id="r1" @change="categoryCheck($event)" value="자유" checked>
          <label for="r1"> 자유</label>
          <input type="radio" name="radioBtn" id="r2" @change="categoryCheck($event)" value="질문">
          <label for="r2"> 질문</label>
        </div>
      </div>
      <div class="form-group" v-if="this.category === '질문'">
        <div class="stacktitle">기술 스택 및 협업 툴</div>
        <div class="checkbox">
          <div>
            <v-checkbox v-model="stacks.javascript" label="JavaScript"/>
            <v-checkbox v-model="stacks.c" label="C"/>
            <v-checkbox v-model="stacks.kotlin" label="Kotlin"/>
            <v-checkbox v-model="stacks.java" label="Java"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.react" label="React"/>
            <v-checkbox v-model="stacks.cpp" label="C++"/>
            <v-checkbox v-model="stacks.django" label="Django"/>
            <v-checkbox v-model="stacks.spring" label="Spring"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.vue" label="Vue"/>
            <v-checkbox v-model="stacks.python" label="Python"/>
            <v-checkbox v-model="stacks.go" label="Go"/>
            <v-checkbox v-model="stacks.flutter" label="Flutter"/>
          </div>
          <div>
            <v-checkbox v-model="stacks.node" label="Node"/>
            <v-checkbox v-model="stacks.typescript" label="TypeScript"/>
            <v-checkbox v-model="stacks.swift" label="Swift"/>
            <v-checkbox v-model="stacks.etc" label="Etc"/>
          </div>
        </div>
      </div>
      <div class="detail1 form-group">
        <label for="content">내용</label>
        <br>
        <textarea class="form-control" type="text" id="content" v-model="content" placeholder=" 내용를 입력하세요"></textarea>
      </div>
      <div v-if="this.category === '질문'" class="detail2 form-group">
        <Tiptap @code-save="codesave"/>
        <!-- <label for="code">코드 입력</label>
        <br>
        <textarea class="form-control" type="text" id="code" v-model="code" placeholder=" 코드를 입력하세요"></textarea> -->
      </div>
      <div class="submitbtn">
        <button class="cancel" @click="back()">취소</button>
        <button class="submit">등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import Tiptap from '../writeeditor/Tiptap.vue'
import { api } from '../../../../api.js'
import axios from 'axios'
import _ from 'lodash'

export default {
  name: 'WriteBoard',
  components: {
    Tiptap,
  },
  data () {
    return {
      title: null,
      content: null,
      code: null,
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
      images: [
        '#FC8F8F',
        '#FFAB5E',
        '#83E38C',
      ],
      img: null,
      skills: [],
      category: '자유',
    }
  },
  methods: {
    back () {
      this.$router.replace()
    },

    codesave(value){
      this.code = value
      // console.log(this.code)
    },

    categoryCheck (event) {
      let selected = event.target.value;
      // console.log("selected : ", selected);
      this.category = selected
      // console.log(this.category)
    },

    stacksCheck () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] !== false){
          this.skills.push(property)
        }
      }
    },

    // 체크한 스텍들 초기화
    stackInit () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] === true){
          this.stacks[property] = false
        }
      }this.skills = []
    },

    onSubmit(event) {
      event.preventDefault()
      if (this.category === '자유') {
        // console.log(this.category)
        if (0 < this.title.length && this.title.length <= 50) {
          if (10 < this.content.length) {
              const token = localStorage.getItem('jwt')
              this.img = _.sample(this.images)
              axios({
                url: api.CREATE_FREE_BOARD,
                method: 'POST',
                data: {
                  cboardTitle: this.title,
                  cboardType: this.category,
                  cboardTechstack: this.skills,
                  cboardContent: this.content,
                  cboardCode: this.code,
                  cboardImg: this.img
                },
                headers: {
                  Authorization: 'Bearer ' + token
                },
              }).then(()=>{
                // console.log(res)
                this.$router.go();
              }).catch(err=>{
                console.error(err)
              })
          }
          else {
            alert('내용을 10자 이상 입력해주세요')
            this.stackInit()
          }
        } 
        else {
          alert("제목은 50자 이하로 입력하세요.")
          this.stackInit()
        }  
      }
      else {
        this.stacksCheck()
        // console.log(this.category)
        if (0 < this.title.length && this.title.length <= 50) {
          if (10 < this.content.length) {
            if (0 < this.skills.length && this.skills.length <= 4) {
              const token = localStorage.getItem('jwt')
              this.img = _.sample(this.images)
              axios({
                url: api.CREATE_FREE_BOARD,
                method: 'POST',
                data: {
                  cboardTitle: this.title,
                  cboardType: this.category,
                  cboardTechstack: this.skills,
                  cboardContent: this.content,
                  cboardCode: this.code,
                  cboardImg: this.img
                },
                headers: {
                  Authorization: 'Bearer ' + token
                },
              }).then(()=>{
                // console.log(res)
                this.$router.go();
              }).catch(err=>{
                console.error(err)
              })
            }
            else {
              alert("기술 스택 및 협업 툴을 1개 이상 4개 이하 선택해주세요")
              this.stackInit()
            }
          }
          else {
            alert('내용을 10자 이상 입력해주세요')
            this.stackInit()
          }
        } 
        else {
          alert("제목은 50자 이하로 입력하세요.")
          this.stackInit()
        }  
      }
    },
  }
}
</script>

<style scoped>

.writeboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

form {
  margin: 2vh 0 0 1vw;
}

form div label {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form div .stacktitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form div .categorytitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form div .categorybox {
  margin: 0 0 3vh 0;
}
form div .categorybox input{
  margin: 0 1vh 0 0;
  width: 1.78vw;
  height: 1.78vh;
}
form div .categorybox label{
  margin: 0 2vh 0 0;
  font-size: 1vw;
  font-weight: 500;
  font-family: 'Epilogue', sans-serif;
}

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 42vw;
}

form .detail1 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 42vw;
  margin-bottom: 2vh;
}

form .checkbox {
  margin: 0 0 2vh 0;
  display: grid;
  grid-template-columns: 12vw 12vw 12vw 12vw;
  font-family: 'Epilogue', sans-serif;
}

form .checkbox div{
  margin: 0;
}

.submitbtn {
  margin: 0 0 2vh 0;
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