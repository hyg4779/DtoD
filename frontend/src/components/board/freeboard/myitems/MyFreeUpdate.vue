<template>
  <div class="updateboard">
    <div class="title">
      게시글 수정
    </div>
    <form>
      <div class="form-group">
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="detail form-group">
        <label for="content">스터디 규칙</label>
        <br>
        <textarea class="form-control" type="text" id="content" v-model="content" placeholder=" 스터디 규칙을 입력하세요"></textarea>
      </div>
      <div class="submitbtn">
        <button class="cancel" @click="back">취소</button>
        <button class="submit" @click="updateFin">등록</button>
      </div>
    </form>
  </div>
    
</template>

<script>
import { api } from '../../../../../api.js'
import axios from 'axios'

export default {
  name: 'UpdateItem',
  props: {
    itempk: Number,
  },
  data () {
    return {
      title: null,
      content: null,
      code: null,
      skills: [],
      category: null,
    }
  },

  methods:{
    back (event) {
      // this.$router.replace()
      event.preventDefault()
      this.$emit('close-modal')
    },
    stacksCheck () {
      for (let property in this.stacks){
        if (this.stacks[property] !== false){
          this.skills.push(property)
        }
      }
    },
    updateFin(event) {
      event.preventDefault()
      if (0 < this.title.length && this.title.length <= 50) {
        if (10 < this.content.length) {
          const token = localStorage.getItem('jwt')
          axios({
            url: api.UPDATE_FREE_BOARD,
            method: 'PUT',
            data: {
              cboardId: this.itempk,
              cboardTitle: this.title,
              cboardType: this.category,
              cboardContent: this.content,
              cboardCode: this.code,
              cboardTechstack: this.skills,
            },
            headers: {
              Authorization: 'Bearer ' + token
            },
          }).then(()=>{
            this.$emit('update-fin')
          }).catch(err=>{
            console.error(err)
          })
        }
        else {
          alert('내용을 10자 이상 입력해주세요')
        }
      } 
      else {
        alert("제목은 1자 이상 50자 이하로 입력하세요.")
      }  
    },
  },
  created() {
    // console.log(this.itempk)
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_FREE_BOARD_DETAIL + `${this.itempk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.title = res.data.cboardTitle
      this.category = res.data.cboardType
      this.content = res.data.cboardContent
    }).catch((err)=>{
      console.error(err)
    })
  }
}

</script>

<style scoped>

.updateboard .title {
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
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
}

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 45rem;
}

form .detail textarea {
  margin: 0 0 2vh 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 30vh;
  width: 45rem;
  margin-bottom: 2vh;
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
</style>