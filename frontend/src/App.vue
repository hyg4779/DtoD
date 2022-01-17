<template>
  <div>
    <Navbar />
  </div>
</template>

<script>
import { ref } from 'vue'
import Navbar from '@/views/Navbar.vue'

export default {
  name: 'LayoutDefault',
  components:{
    Navbar,
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  }
}
</script>
