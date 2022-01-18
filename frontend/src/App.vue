<template>
  <div id="app">
    <Navbar />
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/Home/Navbar.vue'

export default {
  name: 'App',
  components:{
    Navbar,
  },

}
</script>

<style>

</style>