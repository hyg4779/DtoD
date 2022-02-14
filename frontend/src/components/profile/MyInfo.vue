<template>
  <div class="myinfo">
    <!-- <div> -->
      <div class="profileicon">
        <img v-if="picture" :src="picture">
        <img v-else src="../../assets/default_user.png">
      </div>
      <div class="profilebutton">
        <!-- <button class="imgadd" @click="selectUploadFile()">이미지 등록</button> -->
        <label for="profileimg">이미지선택</label>
        <input id="profileimg" type="file" accept="image/*" @change="previewImage" /> 
        <button class="imgadd"  @click="onUpload">이미지등록</button>
      </div>
      <div v-if="uploadValue != 0" class="progress">업로드: {{ uploadValue.toFixed() + "%" }}</div>
    <!-- </div> -->
    <b-form class="form">
      <div class="nickname">
        <div>
          <label class="nicknamelabel" for="nickname">닉네임</label>
          <b-form-input
            id="nickname"
            v-model="credentials.nickname"
            type="text"
            required
          >
          </b-form-input>
        </div>
        <div class="doublecheck">
          <button @click="doubleCheck">중복체크</button>
        </div>
      </div>
      <label class="joblabel" for="input-with-list">희망 직무를 선택하세요</label>
      <b-form-input
        v-model="credentials.jobs"
        list="input-list"
        id="input-with-list">
      </b-form-input>
      <b-form-datalist
        id="input-list"
        :options="options">
      </b-form-datalist>
      <label class="skilllabel" for="checkbox">관심있는 기술 스택을 선택하세요</label>
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
      <div id="btn_group">
        <button @click="onSubmit" class="sumbit_btn" pill >수정 완료</button>
      </div>
    </b-form>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
import firebase from 'firebase/compat/app';
import 'firebase/compat/storage'

export default {
  name: 'MyInfo',
  data() {
    return {
      uploadValue : 0,
      picture: null,
      imageData: null,

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

      options: [
        '웹 프로그래머',
        '웹 퍼블리셔',
        '프론트엔드 엔지니어',
        '서버 개발자',
        '백엔드 엔지니어',
        '데이터 엔지니어',
        '데브옵스 엔지니어',
      ],

      credentials: {
        nickname: null,
        jobs: null,
        skills: [],
      },
      tech: '',
      userEmail: '',
      userName: '',
      userJobs: '',
      nicknameCheck: null
    }
  },
  methods: {
    previewImage(event) { 
      this.uploadValue=0; 
      this.picture=null; 
      this.imageData = event.target.files[0]; 
    }, 
    onUpload( ){ 
      // this.picture=null;
      const storageRef = firebase.storage().ref(`${this. imageData.name}`).put(this.imageData); 
      storageRef.on( `state_changed` ,snapshot=>{ 
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100; 
      }, error=>{console.log(error.message)}, 
      ()=>{this.uploadValue=100; 
      storageRef.snapshot.ref.getDownloadURL().then((url)=>{ 
        this.picture =url; 
        // console.log(url)
        alert("수정완료 버튼을 눌러서 저장해주세요")
        }); 
      }
      )
    },
    doubleCheck (event) {
      event.preventDefault()
      const token = localStorage.getItem('jwt')
      if (this.credentials.nickname !== null && this.credentials.nickname.length >= 2) {
        axios({
          url: api.NICKNAME_CHECK + `${this.credentials.nickname}`,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          console.log(res)
          if (res.data === true) {
            this.nicknameCheck = res.data
            // console.log(this.nicknameCheck)
            alert('중복되는 닉네임이 있습니다.')
          }
          else {
            this.nicknameCheck = res.data
            // console.log(this.nicknameCheck)
            alert('중복되는 닉네임이 없습니다.')
          }
        }).catch(err=>{
          console.log(err)
        })     
      }
      else {
        alert("닉네임을 2자 이상 입력하세요")
      }
    },
    stacksCheck () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] !== false){
          this.credentials.skills.push(property)
        }
      }
    },
    stackCheckOut () {
      this.credentials.skills = []
    },
    stackFalse () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] === true){
          this.stacks[property] = false
        }
      }
    },
    onSubmit(event) {
      this.stacksCheck()
      // console.log(this.nicknameCheck)
      event.preventDefault()
      if (this.nicknameCheck !== null && this.nicknameCheck === false) {
        if (0 < this.credentials.skills.length) {
          if (this.credentials.jobs !== null && this.options.includes(this.credentials.jobs)) {
            const token = localStorage.getItem('jwt')
            axios({
              url: api.USER_INFO_CHANGE,
              method: 'PUT',
              data: {
                userEmail: this.userEmail,
                userImg: this.picture,
                userJobs: this.credentials.jobs,
                userName: this.credentials.nickname,
                userTechstack: this.credentials.skills
              },
              headers: {
                Authorization: 'Bearer ' + token
              },
            }).then(()=> {
              this.$router.go();
            }).catch(err=>{
              console.error(err)
            })
          }
          else {
            this.stackFalse()
            this.stackCheckOut()
            this.nicknameCheck = null
            this.credentials.jobs = this.userJobs
            alert("직업 선택을 리스트에서 선택해주세요")
            const token = localStorage.getItem('jwt')
            axios ({
              method: 'get',
              url: api.USER_INFO_GET,
              headers: { 
                Authorization: 'Bearer ' + token
              }
            }).then(res=>{
              this.tech = res.data.userTechstack
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
            }).catch(err=> {
              console.log(err)
            })
          }
        }
        else {
          this.stackFalse()
          this.stackCheckOut()
          this.nicknameCheck = null
          this.credentials.jobs = this.userJobs
          alert("기술 스택 및 협업 툴을 1개 이상 선택해주세요")
          const token = localStorage.getItem('jwt')
          axios ({
            method: 'get',
            url: api.USER_INFO_GET,
            headers: { 
              Authorization: 'Bearer ' + token
            }
          }).then(res=>{
            this.tech = res.data.userTechstack
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
          }).catch(err=> {
            console.log(err)
          })
        }
      }
      else if (this.userName === this.credentials.nickname) {
        // this.nicknameCheck = true
        if (0 < this.credentials.skills.length) {
          if (this.credentials.jobs !== null && this.options.includes(this.credentials.jobs)) {
            const token = localStorage.getItem('jwt')
            axios({
              url: api.USER_INFO_CHANGE,
              method: 'PUT',
              data: {
                userEmail: this.userEmail,
                userImg: this.picture,
                userJobs: this.credentials.jobs,
                userName: this.credentials.nickname,
                userTechstack: this.credentials.skills
              },
              headers: {
                Authorization: 'Bearer ' + token
              },
            }).then(()=> {
              this.$router.go();
            }).catch(err=>{
              console.error(err)
            })
          }
          else {
            this.stackFalse()
            this.stackCheckOut()
            this.nicknameCheck = null
            this.credentials.jobs = this.userJobs
            alert("직업 선택을 리스트에서 선택해주세요")
            const token = localStorage.getItem('jwt')
            axios ({
              method: 'get',
              url: api.USER_INFO_GET,
              headers: { 
                Authorization: 'Bearer ' + token
              }
            }).then(res=>{
              this.tech = res.data.userTechstack
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
            }).catch(err=> {
              console.log(err)
            })
          }
        }
        else {
          this.stackFalse()
          this.stackCheckOut()
          this.nicknameCheck = null
          this.credentials.jobs = this.userJobs
          alert("기술 스택 및 협업 툴을 1개 이상 선택해주세요")
          const token = localStorage.getItem('jwt')
          axios ({
            method: 'get',
            url: api.USER_INFO_GET,
            headers: { 
              Authorization: 'Bearer ' + token
            }
          }).then(res=>{
            this.tech = res.data.userTechstack
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
          }).catch(err=> {
            console.log(err)
          })
        }
      }
      else {
        this.stackFalse()
        this.stackCheckOut()
        this.nicknameCheck = null
        alert('닉네임 중복검사를 해주세요')
        const token = localStorage.getItem('jwt')
        axios ({
          method: 'get',
          url: api.USER_INFO_GET,
          headers: { 
            Authorization: 'Bearer ' + token
          }
        }).then(res=>{
          this.tech = res.data.userTechstack
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
        }).catch(err=> {
          console.log(err)
        })
      }
    },
  },
  created() {
    const token = localStorage.getItem('jwt')

    axios ({
      method: 'get',
      url: api.USER_INFO_GET,
      headers: { 
        Authorization: 'Bearer ' + token
      }
    }).then(res=>{
      // console.log(res)
      this.credentials.nickname = res.data.userName
      this.credentials.jobs = res.data.userJobs
      this.tech = res.data.userTechstack
      this.userName = res.data.userName
      this.userEmail = res.data.userEmail
      this.userJobs = res.data.userJobs
      this.picture = res.data.userImg
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
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>

<style scoped>
.myinfo {
  margin: 0 auto;
  font-family: 'Epilogue', sans-serif;
  /* width: calc(100vw - 13vw); */
}

.profileicon {
  margin: 3vh auto 0;
  width : 13vh;
  height : 13vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

.profilebutton{
  display: flex;
}

.profilebutton label {
  display: inline-block;
  line-height: normal; 
  vertical-align: middle;
  text-align: center;
  padding: 0.8vh 0.75vw;

  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: #24274A;
  height: 4vh;
  width: 5.3vw;
  margin: 2vh 1vh 0 37.5vw;
  border: 1px solid;
  background-color: white;
  border-radius: 0.8rem;
}
.profilebutton input[type="file"] { /*파일 필드 숨기기*/
  position: absolute; 
  width: 1px; 
  height: 1px; 
  padding: 0; 
  margin: -1px; 
  overflow: hidden; 
  clip:rect(0,0,0,0); border: 0;
}

.profilebutton .imgadd {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 5.3vw;
  margin: 2vh 0 0 0;
  background-color: #24274A;
  border-radius: 0.8rem;
}

.progress {
  margin: 0.5vh 0 0 41.5vw;
  font-weight: bold;
}

.nicknamelabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 2vh 0 0 31.8vw;
}
.joblabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 0 0 -3.5vh 31.8vw;
}
.skilllabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 2vh 0 -2vh 35vw;
}

.form{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /* padding: 0.1rem !important; */
  font: 'Roboto', sans-serif;
  min-height: 550px !important;
  margin: 0 0 0 1vw;
}

.nickname {
  display: flex;
}
.nickname .doublecheck button{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  text-align: center;
  border: 1px solid;
  color: #24274A;
  height: 4vh;
  width: 5vw;
  margin: 5.8vh 0 0 1vw;
  background-color: white;
  border-radius: 0.8rem;
}

#nickname{
  width: 20vw;
  height: 6vh;
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
  margin: 0 0 0 31.8vw;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

#input-with-list{
  width: 20vw;
  height: 6vh;
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
  margin: 0 0 0 31.8vw;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

.checkbox {
  margin: 0 0 0 25vw;
  display: grid;
  grid-template-columns: 10vw 10vw 10vw 10vw;
}

.checkbox div{
  margin: 0;
  padding: 0;
}

.sumbit_btn{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 5vw;
  margin: 0 0 0 40vw;
  background-color: #24274A;
  border-radius: 0.8rem;
}
</style>
