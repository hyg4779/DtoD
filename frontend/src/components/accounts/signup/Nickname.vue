<template>
  <div 
    class="signupform"
    >
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="nick_name"
        label="닉네임 *"
        lazy-rules
        hint="4-12자 내외로 자유롭게 사용하세요"
        :rules="[
          val => val && val.length > 0 || '필수 값입니다',
          val => val.length > 3 && val.length < 13 || '글자수를 맞춰주세요'
        ]"
        
      />
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
  emits:['nickname-fin'],
  setup (props, {emit}) {

    const nick_name = ref(null)
    return {
      nick_name,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

      onSubmit () {
        if (nick_name.value.length > 3 && nick_name.value.length < 13) {
          console.log(props.user)
            alert('Success')
            emit('nickname-fin', nick_name.value)

        }
        else {
          alert('기준에 맞게 입력해 주세요')
        }
      },

      onReset () {
        nick_name.value = null
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