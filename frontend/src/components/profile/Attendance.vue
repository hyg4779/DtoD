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
    <div data-app>
      <Calendars dark="true"/>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import Calendars from './Calendars.vue';

export default {
  name: 'Attendance',
  components: {
    Calendars,
  },
  data() {
    return {
      userImg: null,
    };
  },
  methods: {  
    },

}
</script>

<style scoped>
.profileicon {
  margin: 2vh 0 0 17vw;
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
  margin: 5vh 0 0 0; 
  font-size: 2vh; 
  text-align:center; 
  font-style: Italic; 
  font-weight: bold;
}
</style>