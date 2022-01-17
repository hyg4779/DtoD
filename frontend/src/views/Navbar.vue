<template>
  <div class="nav">
    <p>DtoD</p>
    <section @mouseover="listTwo = true" @mouseleave="listTwo = false">
        <q-avatar>
          <img src="../assets/default_user.png">
        </q-avatar>
        <transition name="fade">
          <ul v-if="listTwo" @click="listTwo = false">
            <router-link :to="{name:'Login'}">Login</router-link>
      
          </ul>        
        </transition>    
    </section>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  components:{

  },
  data(){
    return{
      listOne:   false,
      listTwo:   false,
      listThree: false,
      listFour:  false
    }
  }
}

</script>

<style>
.nav {
  display: flex;
  background-color: white;
  flex-direction: row;
  justify-content: space-between;
}
.menu {  
  font: 14px/1.5 'Open Sans', sans-serif;
  font-weight: 600;
  margin: 0;
  padding: 0;
  list-style: none;
}

.menu a {
  display: block;
  padding: 20px;
  color: #fff;
  text-decoration: none;
}

.menu li {
  display:block;
  float: left;
  position: relative;
  background: #222;
  color: #fff;
  min-width: 180px;
}

.menu li ul {
  position: absolute;
  left: 0;
  top: 61px;
  margin: 0;
  padding: 0;
}

.menu li ul li {
  background: #333;
  transition: .2s;
}

.menu li ul li:hover {
  background: #444;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .2s;
}
.fade-enter, .fade-leave-active {
  opacity: 0;
}
</style>