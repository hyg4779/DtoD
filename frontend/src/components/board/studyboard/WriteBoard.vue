<template>
  <div class="writeboard">
    <div class="title">
      스터디 모집
    </div>
    <form @submit="onSubmit">
      <div class="form-group">
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="people form-group">
        <div class="peopletitle">인원</div>
        <div class="peoplecount">
          <input type="button" class="minusbtn" @click='count("minus")' value="-">
          <div id='result'>0</div>
          <input type="button" class="plusbtn" @click='count("plus")' value='+'>
        </div>
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
      <div class="ing form-group">
        <div class="ingtitle">수행기간</div>
        <IngRange />
      </div>
      <div class="join form-group">
        <div class="jointitle">모집기간</div>
        <JoinRange />
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
        <button class="submit">등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'
import IngRange from './IngRange.vue'
import JoinRange from './JoinRange.vue'
import _ from 'lodash'
import { mapState } from 'vuex'


export default {
  name: 'WriteBoard',
  components: {
    IngRange,
    JoinRange,
  },
  data () {
    return {
      title: '',
      content1: '',
      content2: '',
      content3: '',

      peopleCount: 0,

      images: [
        '#FC8F8F',
        '#FFAB5E',
        '#83E38C',
      ],
      img: '',
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
    }
  },
  computed: {
    // mapState로 joindate와 indate를 추출해서 사용
    ...mapState([
      'date'
    ]),
  },
  methods: {

    // 스터디 모집글 취소
    back () {
      this.$router.replace()
    },
    count(type) {
    // 결과를 표시할 element
      const resultElement = document.getElementById('result');
      // 현재 화면에 표시된 값
      let number = resultElement.innerText;
      // 더하기/빼기
      if(type === 'plus') {
        number = parseInt(number) + 1;
      }else if(type === 'minus')  {
        number = parseInt(number) - 1;
      }
      // 결과 출력
      this.peopleCount = number;
      // console.log(this.peopleCount)
      resultElement.innerText = number;
    },

    // 체크한 스텍들 추출
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
      console.log(this.date.indate)
    },
    onSubmit(event) {
      event.preventDefault()
      this.stacksCheck()
      if (0 < this.title.length && this.title.length <= 50) {
        if (10 >= this.peopleCount && this.peopleCount > 0) {
          if (0 < this.skills.length && this.skills.length <= 4) {
            if (10 < this.content1.length && 10 < this.content2.length && 10 < this.content3.length) {
              // 진행기간 추출
              // console.log(this.date.ingdate)
              const temp = this.date.ingdate.split(' - ')
              let res1 = []
              for(let i = 0; i < temp.length; i++){
                res1.push(temp[i])
              }
              // console.log(res1)
  
              // 모집기간 추출
              // console.log(this.date.joindate)
              const tmp = this.date.joindate.split(' - ')
              let res2 = []
              for(let i = 0; i < tmp.length; i++){
                res2.push(tmp[i])
              }
              // console.log(res2)
  
              let today = new Date()
              let ingstart = new Date(res1[0])
              let joinstart = new Date(res2[0])
              let joinend = new Date(res2[1])
              // console.log(today)
              // console.log(ingstart)
              // console.log(joinstart)
              // console.log(joinend)
  
              if (ingstart > joinend && joinstart >= today.setHours(0,0,0,0)) {
                const token = localStorage.getItem('jwt')
                this.img = _.sample(this.images)
                axios({
                  url: api.CREATE_STUDY_BOARD,
                  method: 'POST',
                  data: {
                    sboardTitle: this.title,
                    sboardPerson: this.peopleCount,
                    sboardTechstack: this.skills,
                    sboardIngdate: this.date.ingdate,
                    sboardJoindate: this.date.joindate,
                    sboardContent1: this.content1,
                    sboardContent2: this.content2,
                    sboardContent3: this.content3,
                    sboardImg: this.img,
                  },
                  headers: {
                    Authorization: 'Bearer ' + token
                  },
                }).then(()=>{
                  this.$router.go();
                }).catch(err=>{
                  console.error(err)
                })
              }
              else {
                alert("모집기간이 오늘 날짜보다 이전이거나 수행날짜보다 이후이면 안됩니다.")
                this.stackInit()
              }
            }
            else {
              alert('내용을 10자 이상 입력해주세요')
              this.stackInit()
            }
          }
          else {
            alert("기술 스택 및 협업 툴을 1개 이상 4개 이하 선택해주세요")
            this.stackInit()
          }
        }
        else {
          alert("인원을 1명 이상 10명 이하 선택해주세요")
          this.stackInit()
        }
      } 
      else {
        alert("제목은 1자 이상 50자 이하로 입력하세요.")
        this.stackInit()
      }  
    },
  },
  created() {

  }
}
</script>

<style scoped>

.writeboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
  font-family: 'Epilogue', sans-serif;
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

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 42vw;
  font-family: 'Epilogue', sans-serif;
}

form .detail1 textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 42vw;
}

form .detail2 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 42vw;
  margin-bottom: 2vh;
}

form .detail3 textarea {
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

.ing {
  margin: 0 0 3vh 0;
}
.ing .ingtitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

.join {
  margin: 0 0 3vh 0;
}
.join .jointitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

.people {
  margin: 0 0 3vh 0;
}
.people .peopletitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

.people .peoplecount {
  display: flex;
}

.people #result {
  font-size: 1vw;
}

.people .peoplecount .minusbtn {
  cursor: pointer;
  font-family: 'Epilogue', sans-serif;
  font-size: 1vw;
  font-weight: bold;
  color: #24274A;
  padding: 0 0.6vw 0 0.6vw;
  margin: 0 1vw 0 0;
  background-color: white;
  border-radius: 1.1rem;
}
.people .peoplecount .plusbtn {
  cursor: pointer;
  font-family: 'Epilogue', sans-serif;
  font-size: 1vw;
  font-weight: bold;
  color: white;
  padding: 0 0.6vw 0 0.6vw;
  margin: 0 0 0 1vw;
  background-color: #24274A;
  border-radius: 1.1rem;
}
</style>