<template>
  <div class="myinfo">
    <div>
      <div class="profileicon">
        <img src="../../assets/default_user.png">
      </div>
      <div class="profilebutton">
        <button class="imgadd" @click="selectUploadFile()">이미지 등록</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
// import jwt_decode from 'jwt-decode'

export default {
    name: 'MyInfo',
    data() {
      return {
        img: '',
      }
    },
    methods: {
      selectUploadFile() {
        var vue = this
        let elem = document.createElement('input')
        // 이미지 파일 업로드 / 동시에 여러 파일 업로드
        elem.id = 'image'
        elem.type = 'file'
        elem.accept = 'image/*'
        elem.multiple = false
        // 클릭
        elem.click();
        // 이벤트 감지
        elem.onchange = function() {
          const formData = new FormData()

          formData.append('file', this.files[0])

          const token = localStorage.getItem('jwt')
          axios({
            method: 'post',
            url: api.USER_INFO_CHANGE,
            data: formData,
            headers: { 
              'Content-Type': 'multipart/form-data' ,
              Authorization: 'Bearer ' + token
            }
          }).then(response => {
              vue.response = response.data
          }).catch(error => {
              vue.response = error.message
          })
        }
      },
      // }
    },
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
.profileicon {
  margin: 2vh auto 0;
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
.imgadd {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 5vw;
  margin: 2vh 0 0 40.5vw;
  background-color: #24274A;
  border-radius: 0.8rem;
}
</style>