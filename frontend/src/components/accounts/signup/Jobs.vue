<template>
  <div class="signupform">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <div class="q-gutter-md">
        <q-select v-model="jobs" :options="options" label="직무" />
      </div>

      <div class="signupbtn">
        <q-btn label="다음" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat />
      </div>
    </q-form>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  emits:['jobs-fin'],

  setup (props, {emit}) {

    const jobs = ref(null)

    return {
      jobs,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),
      options: [
        '웹 프로그래머',
        '웹 퍼블리셔',
        '프론트엔드 엔지니어',
        '서버 개발자',
        '백엔드 엔지니어',
        '데이터 엔지니어',
        '데브옵스 엔지니어',
      ],

      onSubmit () {
        if (jobs.value.length > 0) {
          console.log(props.user)
          alert('Success')
          emit('jobs-fin', jobs.value)
  
        }

      },

      onReset () {
        jobs.value = null
      },
    }
  }
}

</script>

<style>
.signupform {
  margin: 50px 20px 0 20px !important;
}


.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}
</style>