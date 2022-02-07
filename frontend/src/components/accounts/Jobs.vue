<template>
  <b-form class="jobsform">
    <h1>환영합니다!</h1>

    <div>
      <label for="input-with-list">선호하는 직무를 골라주세요</label>
      
      <b-form-input
        v-model="credentials.jobs"
        list="input-list"
        id="input-with-list">
      </b-form-input>
      
      <b-form-datalist
        id="input-list"
        :options="options">
      </b-form-datalist>
    </div>

    <div id="btn_group">
      <b-button id="sumbit_btn" pill @click="skillsModalOpen">다음</b-button>
    </div>
    
  </b-form>
    
</template>

<script>
export default {
  name: 'Jobs',
  data () {
    return {
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
        jobs: null,
      },
    }
  },

  methods:{
    skillsModalOpen () {
      if(this.credentials.jobs === null){
        this.$swal({
          icon: 'error',
          titleText: '희망 직무를 선택해주세요',
          showConfirmButton: false,
          timer: 1500,
        })
      }else{
        this.$store.dispatch('jobs', this.credentials)
        return this.$emit('skills-modal-open')
      }
    },
  },
}

</script>

<style scoped>
.jobsform{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem !important;
  font: 'Roboto', sans-serif;
  min-height: 600px !important;
}
.jobsform h1{
  margin: 1rem;
  text-align: center;
  /* font-weight: bold; */
  font-family: 'Dohyeon', sans-serif;
}

.jobsform label{
  font-weight: bold;
  color: grey;
  font-size: 1.2rem;
  padding: 0.1rem;
}

.input-with-list{
  margin-top: 0.4rem;
  padding: 1rem !important;
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