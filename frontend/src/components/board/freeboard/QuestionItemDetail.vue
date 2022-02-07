<template>
  <div class="questionitemdetail">
    <div class="cardmodal">
      <div class="detailtitle">
        {{this.title}}
      </div>
      <div class="profilebox">
        <div class="profileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="profilename">username</div>
      </div>
      <div class="tech-control">
        <div class="techstack">
          기술 스택
          <span>
            Python
          </span>
        </div>
        <div class="item-control">
          <button class="update" @click="updateArticle">수정</button>
          <button class="delete" @click="deleteArticle">삭제</button>
        </div>
      </div>
      <div class="content">
        <div class="contenttitle">
          내용
        </div>
        <div class="contentdetail">
          {{this.content}}
        </div>
      </div>
      <div class="code">
        <div class="codetitle">
          코드
        </div>
        <div class="code">
          <Tiptap 
            :codedetail="this.code"
          />
            <!-- :techstack="this.techstack" -->
        </div>
      </div>
      <hr>
      <QuestionComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      />
      <hr>
      <div class="commentprofilebox">
        <div class="commentprofileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="commentprofilename">username</div>
      </div>
      <form @submit="commentSubmit">
        <div class="form-group" style="margin-bottom:10px;">
          <textarea 
            class="form-control"
            placeholder="댓글을 남겨주세요" 
            id="comment" 
            rows="2" 
            v-model="mycomment" 
            @keypress.enter="commentSubmit"
            >
          </textarea>
          <button class="submit">등록</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { dummy } from "../../../../generated.js";
// import axios from 'axios'
// import jwt_decode from 'jwt-decode'
import Tiptap from "../vieweditor/Tiptap.vue"
import QuestionComment from "./QuestionComment.vue"

export default {
  name: 'QuestionItemDetail',
  components: {
    Tiptap,
    QuestionComment
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      content: '',
      code: '',
      userImg: '',

      myComments: '',
      comments: [],
    }
  },
  methods:{
    commentSubmit() {
      
    },
    onParentDeleteComment() {

    },
    deleteArticle() {

    },
    updateArticle() {
      
    }
  },
  created() {
    // console.log(this.item_pk)
    const temp = dummy
    for (let i=0; i < temp.length; i++) {
      if (temp[i].id === this.item_pk) {
        this.title = temp[i].title
        this.content = temp[i].content
        this.code = temp[i].code
      }
    }

  }
}
</script>

<style scoped>
.questionitemdetail{
  width: auto; 
  height: auto; 
  padding:10px !important;
  font-size: 20px;
}
.detailtitle {
  text-align: center;
  font-size: 1.5vw;
  font-weight: bold;
}
.profilebox{
  display: flex;
}
.profileicon {
  margin: 2vh 1vw 2vh 17vw;
  width : 6.5vh;
  height : 6.5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.profilename {
  margin: 3.5vh 0 0 0;
}
.tech-control {
  display: flex;
  justify-content: space-between;
}

.techstack {
  font-weight: 400;
  font-size: 1vw;
  margin:  0.6vh 0 2vh 0;
}
.techstack span {
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0.1vh 0.5vw 0.1vh 0.5vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
}
.item-control .update{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: #24274A;
  height: 3vh;
  width: 3vw;
  margin: 0 1vw 0 0;
  border: 1px solid;
  background-color: white;
  border-radius: 1.1rem;
}
.item-control .delete{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 3vh;
  width: 3vw;
  /* margin: 0 1vw 0 0; */
  background-color: #24274A;
  border-radius: 1.1rem;
}

.content {
  margin: 0 0 2vh 0;
}
.content .contenttitle{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
}
.content .contentdetail {
  font-weight:300;
}
.code .codetitle{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
}

.submit {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.85vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 4vw;
  margin: 1vh 0 0 0;
  background-color: #24274A;
  border-radius: 1.1rem;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 1vh 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.commentprofileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.commentprofilename {
  margin: 0.7vh 0 0 0;
}
</style>