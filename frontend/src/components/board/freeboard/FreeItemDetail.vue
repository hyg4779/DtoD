<template>
  <div class="itemdetail">
    <header>
      <h2>{{title}}</h2>
      <div class="profileicon">
        <img v-if="itemuserImg" :src="itemuserImg"> 
        <img v-else src="../../../assets/default_user.png">
      </div>
      작성자: {{itemuserName}}
    </header>
    <br>
    <div class="img-btn">
      <div class="img-box" :style="style">
        <div class="img-box" >
          <div></div>
        </div>
      </div>
      <div class="btnGroup" v-if="userName === itemuserName">
        <button class="myBtn" id="up" @click="updateArticle">수정</button>
        <button class="myBtn" id="de" @click="deleteArticle">삭제</button>
      </div>
    </div>
    <div class="content">
      <div class="contenttitle">
        내용
      </div>
      <div class="contentdetail">
        <p v-html="getContent(this.content)"></p>
      </div>
    </div>
    <hr>
      <!-- <FreeComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      /> -->
    <hr>
    <div class="commentprofilebox">
      <div class="commentprofileicon">
        <img v-if="userImg" :src="userImg"> 
        <img v-else src="../../../assets/default_user.png">
      </div>
      <div class="commentprofilename">{{userName}}</div>
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
        <button class="myBtn submit" id="sub">등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'
// import FreeComment from "./FreeComment.vue"


export default {
  name: 'ItemDetail',
  components: {
    // FreeComment,
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      tech:'',
      content: '',
      imgPath: '',
      imgs: null,
      style: {
        backgroundColor: this.imgPath
      },

      itemuserName: '',
      itemuserImg: '',
      itemuserEmail: '',
      userImg: '',
      userName: '',

      myComments: '',
      comments: [],
    }
  },
  computed: {
    getSkills: function() {
      const t = this.tech
      const temp = t.split(',')
      let res = []
      for(let i = 0; i < temp.length; i++){
        res.push(temp[i])
      }
      return res
    },

  },
  methods:{

    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    commentSubmit() {
      
    },
    onParentDeleteComment() {

    },
    deleteArticle() {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.DELETE_FREE_BOARD + `${this.item_pk}`,
        method: 'DELETE',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        console.log(res)
        this.$router.go();
      }).catch((err)=>{
        console.error(err)
      })
    },
    updateArticle() {
      this.$emit('update-modal-open', this.item_pk)
    },
  },
  created() {
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_FREE_BOARD_DETAIL + `${this.item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      console.log(res)
      this.itemuserEmail = res.data.user.userEmail
      this.itemuserImg = res.data.user.userImg
      this.itemuserName = res.data.user.userName
      this.title = res.data.cboardTitle
      this.tech = res.data.cboardTechstack
      this.content = res.data.cboardContent
      this.imgPath = res.data.cboardImg
      
      this.style.backgroundColor = res.data.cboardImg
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url:  api.USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName
      this.userImg = res.data.userImg
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.itemdetail{
  width: auto; 
  height:auto; 
  border-radius: 20px !important; 
  padding:10px !important;
  font-size: 20px;
}

header{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  /* margin: 1em; */
}

header h2{
  font-weight: bold;
}

.profileicon {
  margin: 2vh 1vw 2vh 1vw;
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
.profilbox h5 {
  margin: 3.5vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}

.tech-control {
  display: flex;
  justify-content: space-between;
  align-content: center;
}

.content .contenttitle{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 1vh 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}
.content .contentdetail {
  font-weight:300;
  margin: 0 0 2vh 0;
  font-family: 'Epilogue', sans-serif;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
  font-family: 'Epilogue', sans-serif;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 2vh 0;
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
  margin: 0.8vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}

.img-btn {
  display: flex;
  justify-content: space-between;
}

.img-box {
  display: flex;
  margin:  0.6vh 0 2vh 0;
}
.img-box .img-box div{
  margin: 9vh 0 0 0;
  width: 15vw;
  height: 17vh;
}

.btnGroup{
  display: flex;
}

.myBtn {
  border-radius: 1rem;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 0.8vw;
  font-weight: bold;
  height: 3.5vh;
  margin: 0 0.3vw 0 0.3vw;
  padding: 0 0.5vw 0.1vh 0.5vw;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  touch-action: manipulation;
  will-change: transform;
}

.submit {
  margin: 0.7vh 0 0 0;
  padding: 0.5vh 0.5vw 0.8vh 0.5vw;
}

.myBtn:hover {
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.myBtn:active {
  box-shadow: none;
  transform: translateY(0);
}

#up{
  background-color: rgb(30, 200, 30);
}

#de{
  background-color: rgb(250, 100, 100);
}

#sub {
  background-color: rgb(50,100,250);
}
</style>
