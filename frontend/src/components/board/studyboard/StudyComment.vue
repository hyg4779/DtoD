<template>
  <div>
    <hr>
    <div class="comment">
      <div class="commentprofilebox">
        <div class="commentprofileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="commentprofilename">{{ getName }}</div>
        <div class="time">
          <div>{{ this.time }}</div>
        </div>
      </div>
      <button v-if="getName == userName" @click="deleteComment">삭제</button>
    </div>
    <div>
      <div class="commentdetail">{{ getComment }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'

export default {
  data() {
    return {
      userName: '',
      userImg: '',

      time: '',
    }
  },
  props: {
    comment: Object,
    item_pk: Number
  },
  computed: {
    getComment() {
      // console.log(this.comment.scommentContent)
      return this.comment.scommentContent
    },
    getName() {
      // console.log(this.comment.user.userName)
      return this.comment.user.userName
    },

  },
  methods: {
    getTime() {
      const today = new Date()
      const hours = ('0' + today.getHours()).slice(-2); 
      const minutes = ('0' + today.getMinutes()).slice(-2);
      const seconds = ('0' + today.getSeconds()).slice(-2);
      const timeString = hours + ':' + minutes  + ':' + seconds;
      const tmp = this.comment.scommentTime.slice(11,19)
      // console.log(timeString)
      // console.log(tmp)
      // console.log(Number(timeString.slice(0,2)) - Number(tmp.slice(0,2)))
      // console.log(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)))
      // console.log(Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))))
      if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 3) {
        this.time = '오래전'
        return this.time
      }
      if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 2) {
        this.time = '2시간전'
        return this.time
      }
      else if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 1) {
        this.time = '1시간전'
        return this.time
      }
      else if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) < 1) {
        if((Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) > 0)) {
          if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 30) {
            this.time = '30분전'
            return this.time
          }
          else if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 20) {
            this.time = '20분전'
            return this.time
          }
          else if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 10) {
            this.time = '10분전'
            return this.time
          }
          else if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 5) {
            this.time = '5분전'
            return this.time
          }
          else if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 3) {
            this.time = '3분전'
            return this.time
          }
          else if( Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) >= 1) {
            this.time = '1분전'
            return this.time
          }
        }
        else if((Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) < 0)) {
          if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 30) {
            this.time = '30분전'
            return this.time
          }
          else if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 40) {
            this.time = '20분전'
            return this.time
          }
          else if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 50) {
            this.time = '10분전'
            return this.time
          }
          else if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 55) {
            this.time = '5분전'
            return this.time
          }
          else if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 57) {
            this.time = '3분전'
            return this.time
          }
          else if( Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))) >= 59) {
            this.time = '1분전'
            return this.time
          }
        }
        else if ((Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)) === 0)) {
          this.time = '몇초전'
          return this.time
        }
      }
    },
    deleteComment(event) {
      event.preventDefault()
      const comment_id = this.comment.scommentId
      const token = localStorage.getItem('jwt')
      axios({
        url: api.DELETE_STUDY_BOARD_COMMENT + `${comment_id}`,
        method: 'DELETE',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then(()=>{
        this.$emit('onParentDeleteComment')
      }).catch((err)=>{
        console.error(err)
      })
    }
  },
  created() {
    // console.log(this.comment)
    this.getTime()
    // const item_pk = this.item_pk
    const token = localStorage.getItem('jwt')

    this.userImg = this.comment.user.userImg

    axios({
      url: api. USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.comment {
  display: flex; 
  justify-content: space-between;
  font-family: 'Epilogue', sans-serif;
}
.comment button{
  margin: 0 0 4.5vh 0; 
  font-size: 0.8vw;
}
.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 2vh 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.commentprofileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.commentprofilename {
  margin: 0 0 0 0;
  font-size: 1vw;
  font-weight: bold;
}
.commentdetail {
  font-size: 0.9vw;
  font-weight: bold;
}
.time {
  margin: 0 0 0 1vw;
  font-size: 0.8vw;
  font-weight: bold;
  color: gray;
}
</style>