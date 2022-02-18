<template>
  <div>
    <hr>
    <article>
      <div class="commentprofilebox">
        <div class="commentprofileicon">
          <img v-if="getImg" :src="getImg"> 
          <img v-else src="../../../../assets/default_user.png">
        </div>
        <div class="commentprofilename">{{ getName }}</div>
        <div class="time">
          <!-- <div>{{ this.time }}</div> -->
          <div>| {{ getCommentTime }}</div>
        </div>
      </div>
      <a
        v-if="getName == userName"
        @click="deleteComment"
        id="delete"
      >  
        삭제
      </a>
    </article>
    <footer>{{ getComment }}</footer>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../../api.js'

export default {
  data() {
    return {
      userName: '',
      // userImg: '',

      time: '',
    }
  },
  props: {
    comment: Object,
    item_pk: Number
  },
  computed: {
    getImg() {
      // console.log(this.comment)
      return this.comment.user.userImg
    },
    getComment() {
      // console.log(this.comment)
      return this.comment.ccommentContent
    },
    getName() {
      // console.log(this.comment.user.userName)
      return this.comment.user.userName
    },
    getCommentTime(){
      return this.getTime()
    }
  },
  methods: {
    getTime() {
      const today = new Date()
      // console.log(today)
      // console.log(this.comment.ccommentTime)
      const years = today.getFullYear()
      const months = (today.getMonth() + 1);
      const days = (today.getDate())
      // console.log(years)
      // console.log(months)
      // console.log(days)
      const hours = ('0' + today.getHours()).slice(-2); 
      const minutes = ('0' + today.getMinutes()).slice(-2);
      const seconds = ('0' + today.getSeconds()).slice(-2);
      const timeString = hours + ':' + minutes  + ':' + seconds;
      console.log(this.comment.ccommentTime)
      const seoul = new Date(this.comment.ccommentTime)
      function formatDate(date) {
        return date.getFullYear() + '-' + 
          (('0' + (date.getMonth() + 1)).slice(-2)) + '-' + 
          date.getDate() + 'T' + 
          (date.getHours() + 9) + ':' + 
          date.getMinutes();
      }
      console.log(formatDate(seoul))

      const tmp = formatDate(seoul).slice(11,19)
      // const tmp = this.comment.ccommentTime.slice(11,19)
      // console.log(years - Number(this.comment.ccommentTime.slice(0,4)))
      // console.log(months - Number(this.comment.ccommentTime.slice(5,7)))
      // console.log(Number(timeString.slice(0,2)) - Number(tmp.slice(0,2)))
      // console.log(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5)))
      // console.log(Math.abs(Number(timeString.slice(3,5)) - Number(tmp.slice(3,5))))
      if(years - Number(this.comment.ccommentTime.slice(0,4)) > 0) {
        this.time = '오래전'
        return this.time
      }
      else {
        if(months - Number(this.comment.ccommentTime.slice(5,7)) > 0) {
          this.time = '오래전'
          return this.time
        }
        else {
          if(days - Number(this.comment.ccommentTime.slice(8,10)) > 0) {
            this.time = '오래전'
            return this.time
          }
          else {
            if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 0) {
              if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 3) {
                this.time = '오래전'
                return this.time
              }
              else if ((Number(timeString.slice(0,2)) - Number(tmp.slice(0,2))) >= 2) {
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
            }
            else {
              this.time = '오래전'
              return this.time
            }
          }
        }
      }


    },
    deleteComment(event) {
      event.preventDefault()
      const comment_id = this.comment.ccommentId
      const token = localStorage.getItem('jwt')
      axios({
        url: api.DELETE_FREE_BOARD_COMMENT + `${comment_id}`,
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
    // this.getTime()
    const token = localStorage.getItem('jwt')

    // this.userImg = this.comment.user.userImg

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
hr{
  margin: 8px;
}
article {
  display: flex; 
  justify-content: space-between;
  align-content: center;
  font-family: 'Epilogue', sans-serif;
}
#delete{
  display: flex;
  align-items: center ;
  margin: 0;
  padding: 0 .6rem 0 .6rem;
  border-radius: 1rem;
  cursor: pointer;
  font-size: .7vw;
}

#delete:hover{
  transition: all 0.3s ease 0s;
  color: rgb(200, 50, 50);
}

.commentprofilebox{
  display: flex;
  align-items: center;
}
.commentprofileicon {
  margin: 8px;
  width : 36px;
  height : 36px;
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
footer {
  margin: 0 0 0 52px;
  font-size: 0.9vw;
  font-weight: bold;
  color: rgba(0, 0, 0, 0.6);
}
.time {
  margin: 0 0 0.6vh 0.5vw;
  font-size: 0.7vw;
  font-weight: bold;
  color: gray;
}
</style>