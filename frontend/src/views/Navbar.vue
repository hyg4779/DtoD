<template>
  <div class="menubar">
    <div class="logo">
      <a href="/">
        <span>DtoD</span>
      </a>
    </div>
    <ul class="menu">
      <li @mouseover="listOne = true" @mouseleave="listOne = false">
        <a href="#">스터디룸</a>
        <transition name="fade">
          <ul v-if="listOne" @click="listOne = false">
            <li><a href="#">진행중</a></li>
            <li><a href="#">스터디 만들기</a></li>
          </ul>        
        </transition>     
      </li>     
      <li @mouseover="listTwo = true" @mouseleave="listTwo = false">   
        <a href="#">게시판</a>
        <transition name="fade">        
          <ul v-if="listTwo" @click="listTwo = false">
            <li><a href="#">스터디 모집</a></li>
            <li><a href="#">자유 게시판</a></li>
          </ul>
        </transition>
      </li>
      <li @mouseover="listThree = true" @mouseleave="listThree = false">
          <q-avatar class="profile">
            <img src="../assets/default_user.png">
          </q-avatar>
        <transition name="fade">         
          <ul v-if="listThree" @click="listThree = false">
            <!-- <div v-if="logined">
              <li><a href="#">Sub Menu Item</a></li>
              <li><a href="#">Sub Menu Item</a></li>
              <li><a href="#">Sub Menu Item</a></li> 
            </div> -->
            <!-- <div v-else> -->
            <div>
              <li>
                <a href="#">
                  <router-link :to="{ name:'Login' }">
                    로그인을
                    <br>
                    해주세요
                  </router-link>
                </a>
              </li>
            </div>
          </ul>
        </transition>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'navbar',
  components:{

  },
  data(){
    return{
      listOne:   false,
      listTwo:   false,
      listThree: false,
    }
  }
}

</script>

<style>
@font-face {
  font-family: "Dohyeon";
  src: url("../assets/font/BMDOHYEON_ttf.ttf");
}

@font-face {
  font-family: "Hanna";
  src: url("../assets/font/BMHANNAPro.ttf");
}

.menubar {
  display: flex;
  justify-content: space-between;
}

.logo {
  font-size: 40px;
  margin: auto 35px;
  color: #24274A;
}

.logo a {
  color: #24274A;
  text-decoration: none;
  font-family: 'Dohyeon', sans-serif;
}

.menu {  
  font: 14px/1.5 'Roboto', sans-serif;
  margin: 0 3px 0 0;
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
  box-shadow: 3px 3px 3px rgb(192, 192, 192);
  z-index: 1;
}

.menu li ul li {
  font-size: 15px;
  background: rgb(255, 255, 255);
  transition: background .2s;
}

.menu li ul li:hover {
  background: rgb(189, 189, 189);
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .2s;
}
.fade-enter, .fade-leave-active {
  opacity: 0;
}
</style>