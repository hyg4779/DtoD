<template>
  <div class="updateboard">
    <div class="title">
      스터디 모집
    </div>
    <form>
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
         <label for="content1">오픈 카카오톡 (연락처)</label>
        <br>
        <textarea class="form-control" type="text" id="content1" v-model="content1" placeholder=" 연락처를 입력하세요"></textarea>
      </div>
      <div class="detail2 form-group">
         <label for="content2">스터디 소개</label>
        <br>
        <textarea class="form-control" type="text" id="content2" v-model="content2" placeholder=" 스터디 소개를 입력하세요"></textarea>
      </div>
      <div class="detail3 form-group">
        <label for="content3">스터디 규칙</label>
        <br>
        <textarea class="form-control" type="text" id="content3" v-model="content3" placeholder=" 스터디 규칙을 입력하세요"></textarea>
      </div>
      <div class="submitbtn">
        <button class="cancel" @click="back()">취소</button>
        <button class="submit" @click="updateFin">등록</button>
      </div>
    </form>
  </div>
    
</template>

<script>
import { api } from '../../../api.js'
import axios from 'axios'

export default {
  name: 'UpdateItem',
  props: {
    itempk: Number,
  },
  data () {
    return {
      title: '',
      content1: '',
      content2: '',
      content3: '',
      peopleCount: 0,
      ingDate: null,
      joinDate: null,

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
      skills: [],
      tech: '',
    }
  },

  methods:{
    back () {
      this.$router.replace()
    },
    getStack () {
      const token = localStorage.getItem('jwt')
      axios({
        url:  api.GET_STUDY_BOARD_DETAIL + `${this.itempk}`,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        this.tech = res.data.sboardTechstack
        const t = this.tech
        const temp = t.split(',')
        let result = []
        for(let i = 0; i < temp.length; i++){
          result.push(temp[i])
        }
        for(let j = 0; j < result.length; j++){
          for (let k = 0; k < Object.keys(this.stacks).length; k++){
            if (result[j] === Object.keys(this.stacks)[k]){
              this.stacks[Object.keys(this.stacks)[k]] = true
            }
          }
        }
      }).catch((err)=>{
        console.error(err)
      })
    },
    stacksCheck () {
      for (let property in this.stacks){
        if (this.stacks[property] !== false){
          this.skills.push(property)
        }
      }
    },
    stackInit () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] === true){
          this.stacks[property] = false
        }
      }
      this.skills = []
    },
    updateFin(event) {
      event.preventDefault()
      this.stacksCheck()
      if (0 < this.title.length && this.title.length <= 50) {
        if (0 < this.skills.length && this.skills.length <= 4) {
          if (10 < this.content1.length && 10 < this.content2.length && 10 < this.content3.length) {
            const token = localStorage.getItem('jwt')
            axios({
              url: api.UPDATE_STUDY_BOARD,
              method: 'PUT',
              data: {
                sboardId: this.itempk,
                sboardTitle: this.title,
                sboardContent1: this.content1,
                sboardContent2: this.content2,
                sboardContent3: this.content3,
                sboardTechstack: this.skills,
                sboardPerson: this.peopleCount,
                sboardIngdate: this.ingDate,
                sboardJoindate: this.joinDate,
              },
              headers: {
                Authorization: 'Bearer ' + token
              },
            }).then(()=>{
              this.$emit('update-fin')
            }).catch(err=>{
              console.error(err)
            })
          }
          else {
            alert('내용을 10자 이상 입력해주세요')
            this.stackInit()
            this.getStack()
          }
        }
        else {
          alert("기술 스택 및 협업 툴을 1개 이상 4개 이하 선택해주세요")
          this.stackInit()
          this.getStack()
        }
      } else {
        alert("제목은 1자 이상 50자 이하로 입력하세요.")
        this.stackInit()
        this.getStack()
      }  
    },
  },
  created() {
    // console.log(this.itempk)
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_STUDY_BOARD_DETAIL + `${this.itempk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.title = res.data.sboardTitle
      this.tech = res.data.sboardTechstack
      this.peopleCount = res.data.sboardPerson
      this.ingDate = res.data.sboardIngdate
      this.joinDate = res.data.sboardJoindate
      this.content1 = res.data.sboardContent1
      this.content2 = res.data.sboardContent2
      this.content3 = res.data.sboardContent3
      // console.log(this.peopleCount)
      // console.log(this.ingDate)
      // console.log(this.joinDate)
      const t = this.tech
      const temp = t.split(',')
      let result = []
      for(let i = 0; i < temp.length; i++){
        result.push(temp[i])
      }
      for(let j = 0; j < result.length; j++){
        for (let k = 0; k < Object.keys(this.stacks).length; k++){
          if (result[j] === Object.keys(this.stacks)[k]){
            this.stacks[Object.keys(this.stacks)[k]] = true
          }
        }
      }
    }).catch((err)=>{
      console.error(err)
    })
  }
}

</script>

<style scoped>

.updateboard .title {
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
  width: 45rem;
}

form .detail1 textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 45rem;
}

form .detail2 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 45rem;
  margin-bottom: 2vh;
}

form .detail3 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 45rem;
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