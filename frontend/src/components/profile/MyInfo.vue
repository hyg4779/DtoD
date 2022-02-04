<template>
    <div>
      <div>
        <button @click="selectUploadFile()">이미지 선택</button>
      </div>
    </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
// import jwt_decode from 'jwt-decode'

export default {
    name: 'CorsReuqest',
    data() {
        return {
            response: ''
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
      }
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

</style>