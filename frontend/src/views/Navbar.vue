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
          <ul v-if="listThree" @click="listThree = false" class="profile-menu">
            <li>
                <!-- <router-link :to="{ name:'Login' }">
                </router-link> -->
              <a href="#" @click="icon = true">
                  로그인을
                  <br>
                  해주세요
              </a>
              <q-dialog v-model="icon">
                <q-card>
                  <q-card-section class="row items-center q-pb-none">
                    <div class="text-h6">로그인</div>
                    <q-space />
                    <q-btn icon="close" flat round dense v-close-popup />
                  </q-card-section>
                    <div class="loginform">
                      <q-form
                        @submit="onSubmit"
                        @reset="onReset"
                        class="q-gutter-md loginform"
                      >
                        <h3>환영합니다!</h3>
                        <q-input
                          filled
                          v-model="user_id"
                          label="아이디 *"
                          lazy-rules
                          :rules="[ val => val && val.length > 0 || '아이디를 입력하세요']"
                        />
                        <q-input
                          filled
                          type="password"
                          v-model="pwd"
                          label="비밀번호 *"
                          lazy-rules
                          :rules="[
                            val => val !== null && val !== '' || '비밀번호를 입력하세요',
                            val => val > 0 && val < 100 || '비밀번호를 입력하세요'
                          ]"
                        />
                        <div>
                          <q-btn label="Submit" type="submit" color="primary"/>
                          <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
                          <q-btn label="Signup" color="primary"
                            flat class="q-ml-sm"
                            @click="$router.push({name:'Signup'})"
                          />
                          
                        </div>
                      </q-form>
                    </div>
                </q-card>
              </q-dialog>
            </li>
          </ul>
        </transition>
      </li>
    </ul>
  </div>
</template>

<script>
import { useQuasar } from 'quasar'
import {ref} from 'vue'

export default {
  // name: 'navbar',
  // components:{

  // },
  setup(){
    const $q = useQuasar()

    const user_id = ref(null)
    const pwd = ref(null)
    const accept = ref(false)
    return{
      user_id,
      pwd,
      accept,
      listOne:   ref(false),
      listTwo:   ref(false),
      listThree: ref(false),
      icon: ref(false),
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

      onSubmit () {
        if (accept.value !== true) {
          $q.notify({
            color: 'red-5',
            textColor: 'white',
            icon: 'warning',
            message: 'You need to accept the license and terms first'
          })
        }
        else {
          $q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Submitted'
          })
        }
      },

      onReset () {
        user_id.value = null
        pwd.value = null
        accept.value = false
      },
      iconTrue(){
        this.icon.value = true
      }
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
  letter-spacing :3px;
}

.logo a {
  color: #24274A;
  text-decoration: none;
  font-family: 'Dohyeon', sans-serif;
}

.menu {  
  font: 14px/1.5 'Roboto', sans-serif;
  margin: 0 30px 0 0;
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
  box-shadow: 5px 5px 5px rgb(192, 192, 192);
  z-index: 1;
}

.menu li ul li {
  font-size: 15px;
  background: rgb(255, 255, 255);
  transition: background .2s;
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