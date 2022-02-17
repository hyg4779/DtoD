<template>
  <div class="editinfo">
    <div class="title">
      스터디룸 정보 수정
    </div>
    <form @submit="onSubmit">
      <div class="form-group">
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <br>
        <input class="form-control" type="password" id="password" v-model="password" placeholder=" 비밀번호를 입력하세요" />
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
        <div class="stackcheckbox">
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
      <div class="form-group">
        <div class="daytitle">활동 요일</div>
        <div class="daycheckbox">
          <v-checkbox v-model="day.mon" label="월"/>
          <v-checkbox v-model="day.tue" label="화"/>
          <v-checkbox v-model="day.wed" label="수"/>
          <v-checkbox v-model="day.thu" label="목"/>
          <v-checkbox v-model="day.fri" label="금"/>
          <v-checkbox v-model="day.sat" label="토"/>
          <v-checkbox v-model="day.sun" label="일"/>
          <v-checkbox v-model="day.yet" label="추후 협의"/>
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
        <button class="cancel" @click="back">취소</button>
        <button class="submit">등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
import IngRange from '../../../components/board/studyboard/IngRange.vue'

export default {
  name: 'EditInfo',
  props: {
    item_pk: Number
  },
  components: {
    IngRange,
  },
  data() {
    return {
      itempk: null,

      title: '',
      password: '',
      peopleCount: 0,
      content1: '',
      content2: '',
      ingDate: '',
      days: [],
      skills: [],

      tech: '',
      date: '',

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
      day: {
        mon: false, tue: false, wed: false, thu: false, 
        fri: false, sat: false, sun: false, yet: false
      },
    }
  },
  computed: {
    getIngDate() {
      // console.log(this.$store.state.date.ingdate)
      return this.$store.state.date.ingdate
    }
  },
  watch: {
    getIngDate(value) {
      this.ingDate = value
      // console.log(this.ingDate)
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    getStackDay () {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_STUDY_ROOM_DETAIL + `${this.item_pk}`,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        // console.log(res)
        this.tech = res.data.roomTechstack
        this.date = res.data.roomIngday
        // console.log(res.data.roomTechstack)
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
        const d = this.date
        const tmp = d.split(',')
        let ret = []
        for(let i = 0; i < tmp.length; i++){
          ret.push(tmp[i])
        }
        for(let j = 0; j < ret.length; j++){
          for (let k = 0; k < Object.keys(this.day).length; k++){
            if (ret[j] === Object.keys(this.day)[k]){
              this.day[Object.keys(this.day)[k]] = true
            }
          }
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    stacksCheck () {
      for (let property in this.stacks){
        if (this.stacks[property] !== false){
          // console.log(property)
          this.skills.push(property)
        }
      }
      // console.log(this.skills)
    },
    stackFalse () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] === true){
          this.stacks[property] = false
        }
      }
      this.skills = []
    },
    dayCheck () {
      for (let property in this.day){
        // console.log(property)
        if (this.day[property] !== false){
          this.days.push(property)
          if (this.days.includes('yet') && this.days.some(i => ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'].includes(i))) {
            // console.log(this.days)
            alert('요일 선택과 추후 협의를 동시에 체크 할 수 없습니다.')
            this.dayCheckOut()
            this.dayFalse()
          }
        }
      }
    },
    dayFalse () {
      for (let property in this.day){
        // console.log(property)
        if (this.day[property] === true){
          this.day[property] = false
        }
      }
      this.days = []
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
    onSubmit(event) {
      event.preventDefault()
      this.stacksCheck()
      this.dayCheck()
      if (0 < this.title.length && this.title.length <= 50) {
        if( 0< this.password.length && this.password.length <= 10) {
          if (10 >= this.peopleCount && this.peopleCount > 0) {
            if (0 < this.skills.length && this.skills.length <= 4) {
              if(0 < this.days.length) {
                // console.log(this.ingDate)
                const temp = this.ingDate.split(' - ')
                let res1 = []
                for(let i = 0; i < temp.length; i++){
                  res1.push(temp[i])
                }
                // console.log(res1)
                let today = new Date()
                let ingstart = new Date(res1[0])
                // console.log(today)
                // console.log(ingstart)
                if (ingstart >= today.setHours(0,0,0,0)) {
                  const token = localStorage.getItem('jwt')
                  axios({
                    url: api.UPDATE_STUDY_ROOM,
                    method: 'PUT',
                    data: {
                      roomId: this.item_pk,
                      roomTitle: this.title,
                      roomPwd: this.password,
                      roomPerson: this.peopleCount,
                      roomTechstack: this.skills,
                      roomIngdate: this.ingDate,
                      roomIngday: this.days,
                      roomContent1: this.content1,
                      roomContent2: this.content2,
                    },
                    headers: {
                      Authorization: 'Bearer ' + token
                    },
                  }).then(()=>{
                    // console.log(res)
                    this.$router.push('/mystudy')
                  }).catch(err=>{
                    console.error(err)
                  })
                }
                else {
                  alert("활동기간 시작일이 오늘 날짜보다 이전이면 안됩니다.")
                  this.stackFalse()
                  this.dayFalse()
                  this.getStackDay()
                }
              }
              else {
                alert("활동 요일을 1개 이상 선택해주세요.")
                this.stackFalse()
                this.dayFalse()
                this.getStackDay()
              }
            }
            else {
              alert("기술 스택 및 협업 툴을 1개 이상 4개 이하 선택해주세요")
              this.stackFalse()
              this.dayFalse()
              this.getStackDay()
            }
          }
          else {
            alert("인원을 1명 이상 10명 이하 선택해주세요")
            this.stackFalse()
            this.dayFalse()
            this.getStackDay()
          }
        }
        else {
          alert("비밀번호를 1 ~ 10자리로 입력해주세요")
          this.stackFalse()
          this.dayFalse()
          this.getStackDay()
        }
      } 
      else {
        alert("제목은 1자 이상 50자 이하로 입력하세요.")
        this.stackFalse()
        this.dayFalse()
        this.getStackDay()
      }
    },
  },
  created() {
    this.itempk = this.item_pk
    const token = localStorage.getItem('jwt')
    axios({
      url: api.GET_STUDY_ROOM_DETAIL + `${this.itempk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.title = res.data.roomTitle
      this.password = res.data.roomPwd
      this.peopleCount = res.data.roomPerson
      this.tech = res.data.roomTechstack
      this.ingDate = res.data.roomIngdate
      this.date = res.data.roomIngday
      this.content1 = res.data.roomContent1
      this.content2 = res.data.roomContent2
      // console.log(res.data.roomTechstack)
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

      const d = this.date
      const tmp = d.split(',')
      let ret = []
      for(let i = 0; i < tmp.length; i++){
        ret.push(tmp[i])
      }
      for(let j = 0; j < ret.length; j++){
        for (let k = 0; k < Object.keys(this.day).length; k++){
          if (ret[j] === Object.keys(this.day)[k]){
            this.day[Object.keys(this.day)[k]] = true
          }
        }
      }
    }).catch(err=>{
      console.log(err)
    })
  }
}
</script>

<style scoped>

.editinfo .title {
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
  font-size: 1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  line-height: 2.2;
  width: 30vw;
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
  border: 1px solid #24274A;
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

form div #password {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 30vw;
  font-family: 'Epilogue', sans-serif;
}

form div .stacktitle {
  font-weight: bold;
  font-size: 1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form .stackcheckbox {
  margin: 0 0 2vh 0;
  display: grid;
  grid-template-columns: 9vw 9vw 9vw 9vw;
  font-family: 'Epilogue', sans-serif;
}
form .stackcheckbox div{
  margin: 0;
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

form div .daytitle {
  font-weight: bold;
  font-size: 1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form .daycheckbox {
  margin: 0 0 2vh 0;
  display: grid;
  grid-template-columns: 6vw 6vw 6vw 6vw 6vw 6vw 6vw 6vw;
  font-family: 'Epilogue', sans-serif;
}

form .daycheckbox div{
  margin: 0;
}

form .detail1 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  width: 40vw;
  height: 4vh;
}

form .detail2 textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 40vw;
  margin-bottom: 2vh;
}

form .submitbtn {
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