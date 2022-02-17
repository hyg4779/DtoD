<template>
  <div>
    <div style="display: flex; justify-content: center;">
      <div class="profileicon">
        <img v-if="userImg" :src="userImg"> 
        <img v-else src="../../assets/default_user.png">
      </div>
      <div class="profiletext">
        <span>여러분의 출석 내역을 확인하세요.</span>
        <br>
        <span>과거의 나는 어땠을까요?</span>
      </div>
    </div>
    <div class="attend-leave">
      <button class="attend" @click="submitAttend">입실</button>
      <button class="leave" @click="submitLeave">퇴실</button>
    </div>
    <div class="calendar">
      <Calendars
        data-app
        v-if="attend"
       :attend="attend"
       :leave="leave"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
import Calendars from './Calendars.vue';

export default {
  name: 'Attendance',
  components: {
    Calendars,
  },
  data() {
    return {
      userImg: null,
      attend: null,
      leave: null,
    };
  },
  methods: {
    submitAttend() {
      const token = localStorage.getItem('jwt')
      axios({
        url: api. CREATE_ATTENDANCE,
        method: 'POST',
        data: {
          checkDate: ''
        },
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        console.log(res)
        axios({
          url: api.GET_ATTENDANCE,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          console.log(res)
          this.attend = res.data
        }).catch((err)=>{
          console.error(err)
        })
      }).catch((err)=>{
        console.error(err)
      })
    },
    submitLeave() {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.CREATE_LEAVE,
        method: 'POST',
        data: {
          checkoutDate: ''
        },
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        console.log(res)
        axios({
          url: api. GET_LEAVE,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          console.log(res)
          this.leave = res.data
        }).catch((err)=>{
          console.error(err)
        })
      }).catch((err)=>{
        console.error(err)
      })
    }
  },
  created() {
    const token = localStorage.getItem('jwt')

    axios({
      url: api. GET_LEAVE,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      console.log(res)
      this.leave = res.data
      console.log(this.leave)
      axios({
      url: api. GET_ATTENDANCE,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
      }).then((res)=>{
        console.log(res)
        this.attend = res.data
        console.log(this.attend)
      }).catch((err)=>{
        console.error(err)
      })
    }).catch((err)=>{
      console.error(err)
    })



    axios({
      url: api. USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userImg = res.data.userImg
      this.userName = res.data.userName
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.profileicon {
  margin: 3vh 0 0 14vw;
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

.profiletext {
  margin: 6vh 0 0 1vw; 
  font-size: 2vh; 
  text-align:center; 
  font-style: Italic; 
  font-weight: bold;
}

.attend-leave {
  margin: 0 0 1vh 1vw;
}

.attend-leave .attend {
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

.attend-leave .attend:hover {
  color: #24274A;
  border: 1px solid #24274A;
  background-color: white;
  transition: .2s;
}

.attend-leave .leave {
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

.attend-leave .leave:hover {
  color: white;
  background-color: #24274A;
  border: 1px solid #24274A;
  transition: .2s;
}

</style>