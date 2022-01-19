<template>
  <div id="app">
    <Navbar />
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/home/Navbar.vue'

export default {
  name: 'App',
  components:{
    Navbar,
  },

}
</script>

<style>

@font-face {
  font-family: "Dohyeon";
  src: url("./assets/font/BMDOHYEON_ttf.ttf");
}
@font-face {
  font-family: "Hanna";
  src: url("./assets/font/BMHANNAPro.ttf");
}

</style>
