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
        <label for="content">내용</label>
        <br>
        <textarea class="form-control" type="text" id="content" v-model="content" placeholder=" 내용를 입력하세요"></textarea>
      </div>
      <div class="detail2 form-group">
        <Tiptap />
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
import Tiptap from '../editor/Tiptap.vue'
// import { api } from '../../../../api.js'
// import axios from 'axios'

export default {
  name: 'WriteBoard',
  components: {
    Tiptap,
  },
  data () {
    return {
      title: '',
      content: '',
      code: '',
      stacks: {
        javascript: false, c: false, kotlin: false, java: false, 
        react: false, cpp: false, django: false, spring: false, 
        vue: false, cs: false, go: false, flutter: false, 
        node: false, typescript: false, swift: false, etc: false},
      result: [],
    }
  },
  methods: {
    back () {
      this.$router.replace()
    },
    onSubmit(event) {
      event.preventDefault()
      // if (this.title.length <= 100) {
      //   for (let property in this.stacks){
      //     if (this.stacks[property]){
      //       this.result.push(property)
      //     }
      //   }
      //   
      //  여기에는 코드 입력한 것을 담자.
      //
      //   axios({
      //     url: api.CREATE_FREE_BOARD,
      //     method: 'POST',
      //     data: {
      //       title: this.title,
      //       content: this.content,
      //       code: this.code,
      //       result: this.result,
      //     },
      //     headers: {
      //       Authorization: `JWT ${localStorage.getItem('jwt')}`
      //     },
      //   }).then(()=>{
      //     this.$router.push('/freeboard')
      //   }).catch(err=>{
      //     console.error(err)
      //   })
      // } else {
      //   alert("제목은 100자 이하로 입력하세요.")
      // }  
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
}

form div .stacktitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
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

/* form .detail2 textarea {

} */

form .checkbox {
  margin: 0 0 2vh 0;
  display: grid;
  grid-template-columns: 12vw 12vw 12vw 12vw;
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