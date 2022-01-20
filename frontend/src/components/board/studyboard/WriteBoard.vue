<template>
  <div class="writeboard">
    <div class="title">
      스터디 모집
    </div>
    <form action="#">
      <div>
        <span>제목</span>
        <br>
        <input type="text" placeholder="  제목을 입력하세요">
      </div>
      <div>
        <span>인원</span>
      </div>
      <div>
        <span>기술 스택 및 협업 툴</span>
        <div class="checkbox">
          <div>
            <q-checkbox v-model="val01" label="JavaScript"/>
            <q-checkbox v-model="val02" label="C"/>
            <q-checkbox v-model="val03" label="Kotlin"/>
            <q-checkbox v-model="val04" label="Java"/>
          </div>
          <div>
            <q-checkbox v-model="val05" label="React.js"/>
            <q-checkbox v-model="val06" label="C++"/>
            <q-checkbox v-model="val07" label="Django"/>
            <q-checkbox v-model="val08" label="Spring"/>
          </div>
          <div>
            <q-checkbox v-model="val09" label="Vue.js"/>
            <q-checkbox v-model="val10" label="C#"/>
            <q-checkbox v-model="val11" label="Go"/>
            <q-checkbox v-model="val12" label="Flutter"/>
          </div>
          <div>
            <q-checkbox v-model="val13" label="Node.js"/>
            <q-checkbox v-model="val14" label="Typescript.js"/>
            <q-checkbox v-model="val15" label="Swift"/>
            <q-checkbox v-model="val16" label="Etc"/>
          </div>
        </div>
      </div>
      <div>
        <span>수행 기간</span>
      </div>
      <div>
        <span>모집 마감일</span>
      </div>
      <div>
        <span>오픈 카카오톡 (연락처)</span>
        <br>
        <input type="text" placeholder="  연락처를 입력하세요">
      </div>
      <div class="detail1">
        <span>스터디 소개</span>
        <br>
        <input type="text" placeholder="  스터디 소개를 입력하세요">
      </div>
      <div class="detail2">
        <span>스터디 규칙</span>
        <br>
        <input type="text" placeholder="  스터디 규칙를 입력하세요">
      </div>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  name: 'WriteBoard',
  setup () {
    return {
      val01: ref(false),
      val02: ref(false),
      val03: ref(false),
      val04: ref(false),
      val05: ref(false),
      val06: ref(false),
      val07: ref(false),
      val08: ref(false),
      val09: ref(false),
      val10: ref(false),
      val11: ref(false),
      val12: ref(false),
      val13: ref(false),
      val14: ref(false),
      val15: ref(false),
      val16: ref(false),
    }
  }
}
</script>

<style scoped>

.writeboard .title {
  font-size: 30px;
  font-weight: bold;
  color: #0D1350;
  margin: 20px 0 0 20px;
}

form {
  margin: 20px 0 0 20px;
}
form div {
  margin: 10px 0 0 0;
}

form div span {
  font-weight: bold;
  font-size: 20px;
}

form div input {
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 40px;
  width: 700px;
}

form .detail1 input {
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 300px;
  width: 800px;
}

form .detail2 input {
  border: 0.1px solid #C4C4C4;
  border-radius: 10px;
  height: 300px;
  width: 800px;
  margin-bottom: 20px;
}

form .checkbox {
  display: grid;
  grid-template-columns: 150px 150px 150px 150px;
}
form .checkbox div{
  margin-right: 20px;
}
</style>