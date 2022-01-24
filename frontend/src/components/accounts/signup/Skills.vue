<template>
  <div class="signupform">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <div class="q-gutter-sm">
        <q-checkbox v-model="stacks.Python" label="Python"/>
        <q-checkbox v-model="stacks.Java" label="Java"/>
        <q-checkbox v-model="stacks.c" label="C"/>
        <q-checkbox v-model="stacks.cpp" label="C++"/>
        <q-checkbox v-model="stacks.Django" label="Django"/>
        <q-checkbox v-model="stacks.Spring" label="Spring"/>
        <br>
        <q-checkbox v-model="stacks.html" label="HTML"/>
        <q-checkbox v-model="stacks.css" label="CSS"/>
        <q-checkbox v-model="stacks.Js" label="JS"/>
        <q-checkbox v-model="stacks.Vue" label="Vue"/>
        <q-checkbox v-model="stacks.React" label="React"/>
        <q-checkbox v-model="stacks.sql" label="SQL"/>
      </div>

      <div class="signupbtn">
        <q-btn label="다음" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat />
      </div>
    </q-form>
  </div>
</template>

<script>
import { ref,reactive } from 'vue'

export default {
  emits:['stacks-fin'],

  setup (props, {emit}) {
    const stacks = reactive({
        Python: false,
        Java: false,
        c: false,
        cpp: false,
        Spring: false,
        Django: false,
        html: false,
        css: false,
        Js: false,
        Vue: false,
        React: false,
        sql: false,
      })

    return {
      stacks,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

      // 체크한 기술스텍만 배열에 담아 signup으로 보내기
      onSubmit () {
        let result = []

        for (let property in stacks){
          if (stacks[property]){
            result.push(property)
          }
        }
          // console.log(result)
          alert('Success')
          emit('stacks-fin', result)
  
        },

      // 체크한 기술스텍 초기화
      onReset (){
        for (let property in stacks){
          if (stacks[property]){
            stacks[property] = false
          }
        }
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