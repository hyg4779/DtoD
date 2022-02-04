<template>
  <b-form class="nicknameform">
    <h1>환영합니다!</h1>

      <b-form-group class="p-3" id="nickname" label="닉네임" label-for="nickname" description="자유롭게 설정하세요">
        <b-form-input
          id="nickname"
          v-model="credentials.nickname"
          type="text"
          required
        >
        </b-form-input>

      </b-form-group>

      <div id="btn_group">
        <b-button id="sumbit_btn" pill @click="jobsModalOpen">다음</b-button>
      </div>
    
  </b-form>
    
</template>

<script>
export default {
  name: 'Nickname',
  data () {
    return {
      credentials: {
        nickname: null,
      },
    }
  },

  methods:{
    jobsModalOpen(){
      // null값이면 legnth에선 오류가 뜨기떄문에 null값 검사
      if(this.credentials.nickname === null){
        this.$swal({
          icon: 'error',
            titleText: '4글자 이상 10글자 이하로 입력해주세요',
            showConfirmButton: false,
            timer: 1500,
            })
        }else{
          let nick = this.credentials.nickname.length
          if(nick >= 4 && nick <=10){
            this.$store.dispatch('nickName', this.credentials)
          this.$emit('jobs-modal-open')
          return
          }
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

#nickname{
  font-weight:bold !important;
  font-size: 1.2rem;
  padding: 1rem; 
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
