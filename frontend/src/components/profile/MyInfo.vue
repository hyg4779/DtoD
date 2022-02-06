<template>
  <div class="myinfo">
    <div>
      <div class="profileicon">
        <img src="../../assets/default_user.png">
      </div>
      <div class="profilebutton">
        <!-- <button class="imgadd" @click="selectUploadFile()">이미지 등록</button> -->
        <label for="profileimg">이미지 선택</label>
        <input id="profileimg" type="file" accept="image/*" @change="previewImage" /> 
        <button class="imgadd"  @click="onUpload">이미지 등록</button>
      </div>
      <div class="progress">업로드: {{ uploadValue.toFixed() + "%" }}</div>
    </div>
    <b-form class="form">
      <label class="nicknamelabel" for="nickname">닉네임</label>
      <b-form-input
        id="nickname"
        v-model="credentials.nickname"
        type="text"
        required
      >
      </b-form-input>

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
          <v-checkbox v-model="stacks.cs" label="C#"/>
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
        <b-button class="sumbit_btn" pill >수정 완료</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
// import axios from 'axios'
// import { api } from '../../../api.js'
// import jwt_decode from 'jwt-decode'
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
          cs: false,
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
          img: '',
          nickname: '',
          jobs: null,
          skills: [],
        }
      }
    },
    methods: {
      previewImage(event) { 
        this.uploadValue=0; 
        this.picture=null; 
        this.imageData = event.target.files[0]; 
      }, 
      onUpload( ){ 
        this.picture=null; 
        const storageRef = firebase.storage().ref(`${this. imageData.name}`).put(this.imageData); 
        storageRef.on( `state_changed` ,snapshot=>{ 
          this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100; 
        }, error=>{console.log(error.message)}, 
        ()=>{this.uploadValue=100; 
        storageRef.snapshot.ref.getDownloadURL().then((url)=>{ 
          this.picture =url; 
          // console.log(url)
          }); 
        }
        )
      }
    },
    // methods: {
    //   selectUploadFile() {
    //     var vue = this
    //     let elem = document.createElement('input')
    //     // 이미지 파일 업로드 / 동시에 여러 파일 업로드
    //     elem.id = 'image'
    //     elem.type = 'file'
    //     // elem.accept = 'image/*'
    //     elem.accept = ['image/png', 'image/jpeg']
    //     elem.multiple = false
    //     // 클릭
    //     elem.click();
    //     // 이벤트 감지
    //     elem.onchange = function() {
    //       const formData = new FormData()

    //       formData.append('file', this.files[0])

    //       const token = localStorage.getItem('jwt')
    //       axios({
    //         method: 'post',
    //         url: api.USER_INFO_CHANGE,
    //         data: formData,
    //         headers: { 
    //           'Content-Type': 'multipart/form-data' ,
    //           Authorization: 'Bearer ' + token
    //         }
    //       }).then(response => {
    //           vue.response = response.data
    //       }).catch(error => {
    //           vue.response = error.message
    //       })
    //     }
    //   },
    // },
    created() {
        // token에서 유저 상세 정보 뺴옴
        // const token = localStorage.getItem('jwt')
        // console.log(token)
        // console.log(jwt_decode(token))
        // axios ({
        //   method: 'get',
        //   url: api.USER_INFO_GET,
        //   headers: { 
        //     Authorization: 'Bearer ' + token
        //   }
        // }).then(res=>{
        //   console.log(res)
        // }).catch(error => {
        //   console.log(error)
        // })
  }
}
</script>

<style scoped>
.myinfo {
  margin: 0 auto;
}

.profileicon {
  margin: 5vh auto 0;
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
  margin: 1vh 1vh 0 41.5vw;
  font-weight: bold;
}

.nicknamelabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 1vh 0 -2vh 31.8vw;
}
.joblabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 1vh 0 -2vh 31.8vw;
}
.skilllabel {
  font-weight: bold;
  font-size: 0.95vw;
  margin: 1vh 0 0 35vw;
}

.form{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
  min-height: 600px !important;
  /* margin: 0 0 0 30.5vw; */
}

#nickname{
  width: 20vw;
  height: 6vh;
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
  margin: 0.4rem 0 0 31.8vw;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

#input-with-list{
  width: 20vw;
  height: 6vh;
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
  margin: 0.4rem 0 2vh 31.8vw;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

.checkbox {
  margin: 0.4rem 0 2vh 25vw;
  display: grid;
  grid-template-columns: 10vw 10vw 10vw 10vw;
}

.checkbox div{
  margin: 0;
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