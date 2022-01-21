<template>
  <div class="menubar">
    <div class="logo">
      <a href="/">
        <span>DtoD</span>
      </a>
    </div>
    <ul class="menu">
      <li @mouseover="listOne = true" @mouseleave="listOne = false">
        <a href="#">DtoD 소개</a>
        <transition name="fade">
          <ul v-if="listOne" @click="listOne = false">
            <li>
              <router-link :to="{ name: 'AboutUs' }">
                DtoD란?
              </router-link>
            </li>
            <li>
              <router-link :to="{ name: 'HowToUse' }">
                이용 방법
              </router-link>
            </li>
          </ul>        
        </transition>     
      </li>  
      <li @mouseover="listTwo = true" @mouseleave="listTwo = false">
        <a href="#">스터디룸</a>
        <transition name="fade">
          <ul v-if="listTwo" @click="listTwo = false">
            <li>
              <router-link :to="{ name: 'Studying' }">
                진행중
              </router-link>
            </li>
            <li>
              <router-link :to="{ name: 'MakeStudy' }">
                스터디 만들기
              </router-link>
            </li>
          </ul>        
        </transition>     
      </li>     
      <li @mouseover="listThree = true" @mouseleave="listThree = false">   
        <a href="#">게시판</a>
        <transition name="fade">        
          <ul v-if="listThree" @click="listThree = false">
            <li>
              <router-link :to="{ name: 'StudyBoard' }">
                스터디 모집
              </router-link>
            </li>
            <li>
              <router-link :to="{ name: 'FreeBoard' }">
                자유 게시판
              </router-link>
            </li>
          </ul>
        </transition>
      </li>
      <li @mouseover="listFour = true" @mouseleave="listFour = false">
          <q-avatar class="profile">
            <img src="../../assets/default_user.png">
          </q-avatar>
          <transition name="fade">         
          <ul v-if="listFour" @click="listFour = false" class="profile-menu">
            <li>
              <a @click="loginSignal = true">
                로그인
              </a>
            </li>
            <li>
              <a @click="signSignal = true">
                회원가입
              </a>
            </li>
          </ul>
        </transition>
      </li>
      <Login v-model="loginSignal"/>
      <Signup v-model="signSignal"/>
    </ul>
  </div>
</template>

<script>
import {ref} from 'vue'
import Login from '../accounts/Login.vue'
import Signup from '../accounts/Signup.vue'

export default {
  name: 'Navbar',
  components:{
    Login,
    Signup,
  },
  setup(){
    
    return{
      listOne:   ref(false),
      listTwo:   ref(false),
      listThree: ref(false),
      listFour: ref(false),
      loginSignal: ref(false),
      signSignal: ref(false),
    }
  }
}

</script>

<style>

.menubar {
  display: flex;
  justify-content: space-between;
}

.logo {
  font-size: 40px;
  margin: auto 35px;
  color: #24274A;
  letter-spacing :3px;
}

.logo a {
  color: #24274A;
  text-decoration: none;
  font-family: 'Dohyeon', sans-serif;
}

.menu {  
  font: 'Roboto', sans-serif;
  margin: 0 25px 0 0;
  padding: 0;
  list-style: none;
}

.menu li {
  display:block;
  float: left;
  position: relative;
  min-width: 150px;
  font-size: 20px;
}

.menu li:hover{
  cursor: pointer;
}

.menu a {
  display: block;
  padding: 20px;
  color: #616DCC;
  text-decoration: none;
  text-align: center;
  font-weight: bold;  
}

.menu li .profile {
  display:block;
  margin: 11px auto;
  padding: 0;
}

.menu li ul {
  position: absolute;
  left: 0;
  top: 61px;
  margin: 0;
  padding: 0;
  border-radius: 10px;
  box-shadow: 5px 5px 5px rgb(63, 63, 63);
  z-index: 1;
}

.menu li ul li {
  font-size: 15px;
  background: rgb(255, 255, 255);
  transition: .2s;
}

.menu li ul li:first-child {
  border-radius: 10px 10px 0 0;
}

.menu li ul li:last-child {
  border-radius: 0 0 10px 10px;
}

/* .menu li .profile-menu li:first-child {
  border-radius: 10px 10px 10px 10px;
} */

.menu li ul li:hover {
  background: rgb(209, 209, 209);
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .2s;
}
.fade-enter, .fade-leave-active {
  opacity: 0;
}
</style>