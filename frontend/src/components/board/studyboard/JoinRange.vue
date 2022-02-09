<template>
  <div>
    <div class="datepicker-trigger-two">
      <input
        type="text"
        id="datepicker-trigger-two"
        placeholder="Select dates"
        :value="formatDates(dateOne, dateTwo)"
      >

      <AirbnbStyleDatepicker
        class="AirbnbStyleDatepicker"
        :trigger-element-id="'datepicker-trigger-two'"
        :mode="'range'"
        :fullscreen-mobile="true"
        :date-one="dateOne"
        :date-two="dateTwo"
        @date-one-selected="val => { dateOne = val }"
        @date-two-selected="val => { dateTwo = val }"
      />
    </div>
  </div>
</template>

<script>
import format from 'date-fns/format'

export default {
  data() {
    return {
      dateFormat: 'YYYY-MM-DD',
      dateOne: '',
      dateTwo: '',

      date: {
        joindate: ''
      }
    }
  },
  methods: {
    formatDates(dateOne, dateTwo) {
      let formattedDates = ''
      if (dateOne) {
        formattedDates = format(dateOne, this.dateFormat)
      }
      if (dateTwo) {
        formattedDates += ' - ' + format(dateTwo, this.dateFormat)
      }
      this.date.joindate = formattedDates
      this.$store.dispatch('joinDate', this.date)
      return formattedDates
    }
  }
}
</script>

<style>
#datepicker-trigger-two{
  position: relative !important;
  width: 320px !important;
  border: 1px solid;
  border-radius: 0.78rem;
  padding: 0.5vh 1vw 0.5vh 1vw;
}

.AirbnbStyleDatepicker {
  position: absolute !important;;
  top: 20.7rem !important;
  left: 38rem !important;
}

.asd__keyboard-shortcuts-trigger {
  opacity: 0.6;
  display: none;
  cursor: not-allowed !important;
}
</style>