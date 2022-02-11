<template>
  <div class="myinfo">
    <div class="profileicon">
      <img v-if="credentials.userImg" :src="credentials.userImg">
      <img v-else src="../../assets/default_user.png">
    </div>
    <div class="profilebutton">
      <!-- <button class="imgadd" @click="selectUploadFile()">이미지 등록</button> -->
      <label for="profileimg">이미지 선택</label>
      <input id="profileimg" type="file" accept="image/*" @change="previewImage" /> 
      <button class="imgadd"  @click="onUpload">이미지 등록</button>
    </div>
    <div
      v-if="uploadValue != 0"
      class="progress">업로드:
      {{ uploadValue.toFixed() + "%" }}
    </div>
    <b-form class="form">
      <div class="nickname">
        <div>
          <label class="nicknamelabel" for="nickname">닉네임</label>
          <b-form-input
            id="nickname"
            v-model="newName"
            type="text"
            required
          >
          </b-form-input>
        </div>
        <div class="doublecheck">
          <button @click="nameCheck">중복체크</button>
        </div>
      </div>
      <label class="joblabel" for="input-with-list">희망 직무를 선택하세요</label>
      <b-form-input
        v-model="credentials.userJobs"
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
          <v-checkbox v-model="stacks.python" label="Python"/>
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

const token = localStorage.getItem('jwt')

export default {
  name: 'MyInfo',
  data() {
    return {
      uploadValue : 0,  // 이미지 업로드 진행상황 (단위: %)
      picture: null,    // 변경한 이미지를 담는 변수
      imageData: null,  // firebase에 담을 image를 

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
        userEmail: null,
        userImg: null,
        userJobs: null,
        userName: null,
        userTechstack: null,
      },
      newName: null,
      nicknameCheck: null
    }
  },
  methods: {
    // 초기화
    credentialsInit(){

      this.nicknameCheck = null
      this.picture = this.credentials.userImg

      this.credentials = this.$store.state.userInfo

      // v-model로 연동된 닉네임 값
      this.newName = this.credentials.userName    

      // user 기술스텍을 받아서 현재 stack값 초기화
      let userStacks = this.credentials.userTechstack 
      console.log(userStacks)
      for(let property in this.stacks){
        console.log(userStacks.includes(property))
        if (userStacks.includes(property)===true){
          this.stacks[property] = true
        }else{
          this.stacks[property] = false
        }
      }
    },
    //
    previewImage(event) { 
      this.uploadValue = 0; 
      this.picture = null; 
      console.log(this.imageData)
      this.imageData = event.target.files[0]; 
      console.log(this.imageData)
    }, 

    // 이미지 파일 등록 메서드
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

    // 닉네임 중복체크 메서드
    nameCheck () {
      if(this.newName === this.credentials.userName){
        alert('같은 닉네임 입니다')
        return 
      }
      if (this.newName.length >= 2) {
        axios({
          url: api.NICKNAME_CHECK + `${this.credentials.userName}`,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          console.log(res.data) // true면 중복됨
          if (res.data) {
            this.nicknameCheck = false
            alert('현재 사용중인 닉네임입니다.')
          }
          else {
            this.nicknameCheck = true
            alert('사용 가능합니다.')
          }
        }).catch(err=>{
          console.log(err)
        })     
      }
      else {
        alert("닉네임을 2자 이상 입력하세요")
      }
    },

    // 체크한 기술스텍만 담는 메서드
    stacksCheck () {
      for (let property in this.stacks){
        // console.log(property)
        if (this.stacks[property] !== false){
          this.credentials.userTechstack.push(property)
        }
      }
    },

    // 개인정보 수정 메서드
    onSubmit(event) {
      event.preventDefault()

      let userData = this.credentials
      if (this.newName === this.credentials.userName || this.nicknameCheck === true) {
        if (0 < this.credentials.userTechstack.length) {
          if (this.credentials.userJobs !== null && this.options.includes(this.credentials.userJobs)) {
            axios({
              url: api.USER_INFO_CHANGE,
              method: 'PUT',
              data: {
                userData
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
            // 초기화
            this.credentialsInit()
            alert("직업 선택을 리스트에서 선택해주세요")
          }
        }
        else {
          // 초기화
          this.credentialsInit()
          alert("기술 스택 및 협업 툴을 1개 이상 선택해주세요")
        }
      }
      else {
        // 초기화
        this.credentialsInit()
        alert('닉네임 중복검사를 해주세요')
      }
    },
  },
  beforeMount() {
    // vuex에 있는 user 정보 받아옴
    this.credentialsInit()
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