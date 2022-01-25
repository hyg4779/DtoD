<template>
  <div class="writeboard">
    <div class="title">
      스터디 모집
    </div>
    <!-- <form @submit="onSubmit">
      <div>
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="form-control">
        <span>기술 스택 및 협업 툴</span>
        <div class="checkbox">
          <div>
            <q-checkbox v-model="stacks.javascript" label="JavaScript"/>
            <q-checkbox v-model="stacks.react" label="React.js"/>
            <q-checkbox v-model="stacks.vue" label="Vue.js"/>
            <q-checkbox v-model="stacks.node" label="Node.js"/>
          </div>
          <div>
            <q-checkbox v-model="stacks.c" label="C"/>
            <q-checkbox v-model="stacks.cpp" label="C++"/>
            <q-checkbox v-model="stacks.cs" label="C#"/>
            <q-checkbox v-model="stacks.typescript" label="TypeScript.js"/>
          </div>
          <div>
            <q-checkbox v-model="stacks.kotlin" label="Kotlin"/>
            <q-checkbox v-model="stacks.django" label="Django"/>
            <q-checkbox v-model="stacks.go" label="Go"/>
            <q-checkbox v-model="stacks.swift" label="Swift"/>
          </div>
          <div>
            <q-checkbox v-model="stacks.java" label="Java"/>
            <q-checkbox v-model="stacks.spring" label="Spring"/>
            <q-checkbox v-model="stacks.flutter" label="Flutter"/>
            <q-checkbox v-model="stacks.etc" label="Etc"/>
          </div>
        </div>
      </div>
      <div class="detail1">
         <label for="content1">오픈 카카오톡 (연락처)</label>
        <br>
        <textarea class="form-control" type="text" id="content1" v-model="content1" placeholder=" 연락처를 입력하세요"></textarea>
      </div>
      <div class="detail2">
         <label for="content2">스터디 소개</label>
        <br>
        <textarea class="form-control" type="text" id="content2" v-model="content2" placeholder=" 스터디 소개를 입력하세요"></textarea>
      </div>
      <div class="detail3">
        <label for="content3">스터디 규칙</label>
        <br>
        <textarea class="form-control" type="text" id="content3" v-model="content3" placeholder=" 스터디 규칙을 입력하세요"></textarea>
      </div>
      <div class="submitbtn">
        <button class="cancle" @click="back()">취소</button>
        <button class="submit">등록</button>
      </div>
    </form> -->
  </div>
</template>

<script>
// import { reactive } from 'vue'
// import axios from 'axios'

export default {
  name: 'WriteBoard',
  // setup () {
  //   const stacks = reactive({
  //       javascript: false,
  //       c: false,
  //       kotlin: false,
  //       java: false,
  //       react: false,
  //       cpp: false,
  //       django: false,
  //       spring: false,
  //       vue: false,
  //       cs: false,
  //       go: false,
  //       flutter: false,
  //       node: false,
  //       typescript: false,
  //       swift: false,
  //       etc: false,
  //     })
  //   return {
  //     stacks,
  //   }
  // },
  data () {
    return {
      title: '',
      content1: '',
      content2: '',
      content3: '',
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
      //   axios({
      //     url: '',
      //     method: 'POST',
      //     data: {
      //       title: this.title,
      //       content1: this.content1,
      //       content2: this.content2,
      //       content3: this.content3,
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
  font-size: 30px;
  font-weight: bold;
  color: #0D1350;
  margin: 20px 0 0 20px;
}

form {
  margin: 20px 0 0 20px;
}

form div label {
  font-weight: bold;
  font-size: 20px;
}

form div span {
  font-weight: bold;
  font-size: 20px;
}

form div textarea {
  margin: 0 0 20px 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 40px;
  width: 700px;
}

form .detail1 textarea {
  margin: 0 0 20px 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 40px;
  width: 800px;
}

form .detail2 textarea {
  margin: 0 0 20px 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 300px;
  width: 800px;
  margin-bottom: 20px;
}

form .detail3 textarea {
  margin: 0 0 20px 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 300px;
  width: 800px;
  margin-bottom: 20px;
}

form .checkbox {
  margin: 0 0 20px 0;
  display: grid;
  grid-template-columns: 150px 150px 150px 150px;
}
form .checkbox div{
  margin-right: 20px;
}

.submitbtn {
  margin: 0 0 10px 0;
}

.submitbtn .cancle {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 16px;
  font-weight: bold;
  color: #24274A;
  height: 40px;
  width: 80px;
  margin: 0 10px 0 0;
  border: 1px solid;
  background-color: white;
  border-radius: 20px;
}

.submitbtn .submit {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 16px;
  font-weight: bold;
  color: white;
  height: 40px;
  width: 80px;
  margin: 0 10px 0 0;
  border: none;
  background-color: #24274A;
  border-radius: 20px;
}
</style>