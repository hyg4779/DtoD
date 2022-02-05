<template>
  <div>
    <div>
      이미지
    </div>
    <div data-app>
      <Calendars />
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
    };
  },
  methods: {  
    },

}
</script>

<style scoped>

</style>