<template>
  <div class="menubar">
    <div class="logo">
      <a href="/">
        <div>
          DTOD
        </div>
      </a>
    </div>
    <ul class="menu">
    <!-- <ul class="menu"> -->
      <li @mouseover="listOne = true" @mouseleave="listOne = false">
        <a href="#">DTOD 소개</a>
        <transition name="fade">
          <ul v-if="listOne" @click="listOne = false">
            <li>
              <router-link :to="{ name: 'AboutUs' }">
                DTOD란?
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

      <!-- 비로그인 상태 -->
      <li v-if="!getToken" class="profile" @mouseover="listFour = true" @mouseleave="listFour = false">
        <div class="profileicon">
          <img src="../../assets/default_user.png">
        </div>
        <transition name="fade">         
          <ul v-if="listFour" @click="listFour = false" class="profile-menu">
            <li @click="loginModalOpen">
              <a href="#">
                로그인
              </a>
            </li>
          </ul>
        </transition>
      </li>

      <!-- 로그인 상태 -->
      <li v-else class="profile" @mouseover="listFour = true" @mouseleave="listFour = false">
        <div class="profileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../assets/default_user.png">
        </div>
        <transition name="fade">         
          <ul v-if="listFour" @click="listFour = false">
            <li>
              <router-link :to="{ name: 'MyProfile' }">
                프로필
              </router-link>
            </li>
            <li>
              <router-link :to="{ name: 'MyStudy' }">
                내 학습
              </router-link>
            </li>
            <li>
              <router-link class="logoutLink" @click.native="logout" to="#">
                로그아웃
              </router-link>
            </li>
          </ul>
        </transition>
      </li>
    </ul>
 
    <!-- 로그인 모달 -->
    <b-modal
      ref="login"
      id="login"
      centered
      hide-footer 
      hide-header
    >
      <Login
        @signup-modal-open="signupModalOpen"
        @Login="Login"
      />
    </b-modal>

    <!-- 회원가입모달 -->
    <b-modal
      ref="signup"
      centered
      hide-footer 
      hide-header
    >
      <Signup
      @nickname-modal-open="nickNameModalOpen"
      />
    </b-modal>

    <!-- 닉네임모달 -->
    <b-modal
      ref="nickname"
      centered
      hide-footer 
      hide-header
    >
      <Nickname
      @jobs-modal-open="jobsModalOpen"
      />
    </b-modal>
    <!-- 직무 모달 -->
    <b-modal
      ref="jobs"
      centered
      hide-footer 
      hide-header
    >
      <Jobs
      @skills-modal-open="skillsModalOpen"
      />
    </b-modal>
    <!-- 기술스텍 모달 -->
    <b-modal
      ref="skills"
      centered
      hide-footer 
      hide-header
    >
      <Skills
      @signup-fin="signupFin"
      />
    </b-modal>

  </div>
</template>

<script>
import Login from '../accounts/Login.vue'
import Signup from '../accounts/Signup.vue'
import Nickname from '../accounts/Nickname.vue'
import Jobs from '../accounts/Jobs.vue'
import Skills from '../accounts/Skills.vue'

export default {
  name: 'Navbar',
  components:{
    Login,
    Signup,
    Nickname,
    Jobs,
    Skills,

  },
  data () {
    return {
      listOne: false,
      listTwo: false,
      listThree: false,
      listFour: false,
    }
  },
  computed:{
    // 토큰으로 로그인 여부 판단
    getToken(){
      return localStorage.getItem('jwt')
    },

    // 유저의 프로필 이미지
    userImg(){
      if (this.getToken){
        return this.$store.state.userInfo.userImg
      } return null
    }

  },
  methods:{
    Login(){
      this.$swal({
          toast: true,
          position: "top-end",
          width: "13rem",
          icon: 'success',
          titleText: '로그인!',
          showConfirmButton: false,
          timer: 1500,
        })
      this.$refs['login'].hide()
      this.$router.go()
    },
    loginModalOpen(){
      this.$refs['login'].show()
    },
    signupModalOpen() {
      this.$refs['login'].hide()
      this.$refs['signup'].show()
    },
    nickNameModalOpen(){
      this.$refs['signup'].hide()
      this.$refs['nickname'].show()
    },
    jobsModalOpen(){
      this.$refs['nickname'].hide()
      this.$refs['jobs'].show()
    },
    skillsModalOpen(){
      this.$refs['jobs'].hide()
      this.$refs['skills'].show()
    },
    signupFin(){
      this.$refs['skills'].hide()
      this.$swal({
          width: "32rem",
          icon: 'success',
          titleText: '회원가입 성공!',
          text: '로그인을 진행해주세요',
          showConfirmButton: false,
          timer: 2000,
        })

      this.$refs['login'].show()
    },

    logout() {
      localStorage.removeItem('jwt')
      this.$router.push({ name: 'Home' })
      this.$router.go()
      this.$swal({
          toast: true,
          position: "top-end",
          width: "13rem",
          icon: 'success',
          titleText: '로그아웃!',
          showConfirmButton: false,
          timer: 1500,
        })
      
    },
  },

  /* 
  로그인 경우: 유저정보 받아와서 Vuex에 넣기
  비로그인 경우: Navbar default Img 설정
  */
  created() {
    if(this.getToken){
      this.$store.dispatch('getUserInfo')
    }
  }
}

</script>

<style>
.menubar {
  display: flex;
  justify-content: space-between;
  background-color: white;
  height: 7.498vh;
  width: 100%;
}

.logo {
  font-size: 2.2vw;
  letter-spacing :0.15vw;
}

.logo a {
  text-decoration: none;
}

.logo a div{
  margin: 0.8vh 0 0 2.5vw;
  color: #24274A !important;
  font-family: 'Dohyeon', sans-serif;
}

.menu {  
  font: 'Roboto', sans-serif;
  margin: 0 1.5vw 0 0;
  padding: 0;
  list-style: none;
}

.menu li {
  display:block;
  float: left;
  position: relative;
  min-width: 7.8vw;
  font-size: 1vw;
  z-index: 1000;
}

.menu li:hover{
  cursor: pointer;
}

.menu a {
  display: block;
  padding: 2.25vh;
  color: #616DCC;
  text-decoration: none;
  text-align: center;
  font-weight: bold;  
}

.menu li .profileicon {
  margin: 1.3vh auto 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}

.menu li .profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

.menu li ul {
  position: absolute;
  left: 0;
  top: 7vh;
  margin: 0;
  padding: 0;
  border-radius: 10px;
  box-shadow: 5px 5px 5px rgb(63, 63, 63);
  z-index: 1;
}

.menu li ul li {
  font-size: 0.8vw;
  background: rgb(255, 255, 255);
  transition: .2s;
}

.menu li ul li:first-child {
  border-radius: 10px 10px 0 0;
}

.menu li ul li:last-child {
  border-radius: 0 0 10px 10px;
}

.menu li .profile-menu li:first-child {
  border-radius: 10px 10px 10px 10px;
}

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