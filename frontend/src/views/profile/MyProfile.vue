<template>
  <div class="myprofile">
    <div class="sidebar">
      <div @click="clicked">
        <span class="myinfo" :class="{active1: isActive1}">
          개인정보수정
        </span>
      </div>
      <div @click="clicked"> 
        <span class="attendance" :class="{active2: isActive2}">
          출석 현황
        </span>
      </div>
      <!-- <div @click="clicked">
        <span class="studytime" :class="{active3: isActive3}">
          학습시간분석
        </span>
      </div> -->
    </div>
    <div class="mainmenu">
      <div v-if="this.myinfo === true">
        <MyInfo />
      </div>
      <div v-if="this.attendance === true">
        <Attendance />
      </div>
      <!-- <div v-if="this.studytime === true">
        <StudyTime />
      </div> -->
    </div>
  </div>
</template>

<script>
import MyInfo from '../../components/profile/MyInfo.vue'
import Attendance from '../../components/profile/Attendance.vue'
// import StudyTime from '../../components/profile/StudyTime.vue'

export default {
  name: 'MyProfile',
  components: {
    MyInfo,
    Attendance,
    // StudyTime,
  },
  data() {
    return {
      myinfo: true,
      attendance: false,
      // studytime: false,
      isActive1: true,
      isActive2: false,
      // isActive3: false,
    }
  },
  methods: {
    clicked(event) {
      const className = event.target.className
      // console.dir(event.target)
      if (className === 'myinfo') {
        this.myinfo = true
        this.attendance = false
        // this.studytime = false
        this.isActive1 = true
        this.isActive2 = false
        // this.isActive3 = false
      }
      else if (className === 'attendance') {
        this.myinfo = false
        this.attendance = true
        // this.studytime = false
        this.isActive1 = false
        this.isActive2 = true
        // this.isActive3 = false
      }
      // else if (className === 'studytime') {
      //   this.myinfo = false
      //   this.attendance = false
      //   this.studytime = true
      //   this.isActive1 = false
      //   this.isActive2 = false
      //   this.isActive3 = true
      // }
    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      // const token = localStorage.getItem('jwt')
      // console.log(token)
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style scoped>

.myprofile {
  display: flex;
}

.myprofile .sidebar {
  background-color: #24292F;
  text-align: center;
  width: 13vw;
  min-height: calc(100vh - 7.498vh);
}

.myprofile .sidebar div {
  margin: 5vh 0 2vh 0;
  font-size: 0.78vw;
  font-weight: bold;
}

.myprofile .sidebar div span {
  cursor: pointer;
  color: white;
  padding: 1vh 2vw;
}

.myprofile .sidebar div .active1 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.myprofile .sidebar div .active2 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

/* .myprofile .sidebar div .active3 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
} */

.myprofile .sidebar div span:hover{
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.myprofile .mainmenu {
  width: calc(100vw - 13vw);
}

</style>
