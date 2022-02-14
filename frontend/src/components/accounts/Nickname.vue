<template>
  <b-form class="nicknameform">
    <h1>환영합니다!</h1>

      <div class="nickname">
        <b-form-group class="p-3" id="nickname" label="닉네임" label-for="nickname" description="자유롭게 설정하세요">
          <b-form-input
            id="nickname"
            v-model="credentials.nickname"
            type="text"
            required
          >
          </b-form-input>
        </b-form-group>
        <div class="doublecheck">
          <input type="button" @click="doubleCheck" value="중복체크" />
        </div>
      </div>
      <div id="btn_group">
        <b-button id="sumbit_btn" pill @click="jobsModalOpen">다음</b-button>
      </div>
    
  </b-form>
    
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'

export default {
  name: 'Nickname',
  data () {
    return {
      credentials: {
        nickname: null,
      },
      nicknameCheck: null,
    }
  },

  methods:{
    doubleCheck () {
      // event.preventDefault()
      const token = localStorage.getItem('jwt')
      if (this.credentials.nickname !== null && this.credentials.nickname.length >= 4 && this.credentials.nickname.length <= 10) {
        axios({
          url: api.NICKNAME_CHECK + `${this.credentials.nickname}`,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          // console.log(res)
          if (res.data === true) {
            this.nicknameCheck = res.data
            console.log(this.nicknameCheck)
            alert('중복되는 닉네임이 있습니다.')
          }
          else {
            this.nicknameCheck = res.data
            console.log(this.nicknameCheck)
            alert('중복되는 닉네임이 없습니다.')
          }
        }).catch(err=>{
          console.log(err)
        })     
      }
      else {
        alert("닉네임을 4자 이상 10자 이하로 입력하세요")
      }
    },

    jobsModalOpen(){
      // null값이면 legnth에선 오류가 뜨기떄문에 null값 검사
      if (this.nicknameCheck !== null && this.nicknameCheck === false) {
        if(this.credentials.nickname === null){
          this.$swal({
            icon: 'error',
            titleText: `4글자 이상 10글자 이하로 
            입력해주세요`,
            showConfirmButton: false,
            timer: 1500,
          })
          this.nicknameCheck= null
        }else{
          let nick = this.credentials.nickname.length
          if(nick >= 4 && nick <=10){
            this.$store.dispatch('nickName', this.credentials)
          this.$emit('jobs-modal-open')
          return
          }
        }
      }
      else {
        this.nicknameCheck= null
        alert('닉네임 중복검사를 해주세요')
        return
      }
    }
  },
}

</script>

<style scoped>
.nicknameform{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
  min-height: 600px !important;
}
.nicknameform h1{
  margin: 1rem;
  text-align: center;
  /* font-weight: bold; */
  font-family: 'Dohyeon', sans-serif;
}

.nicknameform input{
  margin-top: 0.4rem;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
}

.nickname {
  display: flex;
}
.nickname .doublecheck input{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  text-align: center;
  border: 1px solid;
  color: #24274A;
  height: 4vh;
  width: 5vw;
  margin: 7vh 0 0 1.5vw;
  background-color: white;
  border-radius: 0.8rem;
  box-shadow: none !important;
}


#nickname{
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem;
  width: 17vw;
}

#btn_group{
  display: flex;
  flex-direction: column;
  align-items: center;
}

#sumbit_btn{
  background-color: rgb(50, 50, 255) !important;
  font-weight:bold !important;
  border: none;
  border-radius: 1rem !important;
  box-shadow: 0 0.1rem 0.1rem grey !important;
  max-width: 6rem;
  margin-top: 3rem;
  padding: 0.5rem 1rem 0.5rem 1rem;

}

#sumbit_btn:hover{
  background-color: rgb(75, 75, 255) !important;
}
</style>
