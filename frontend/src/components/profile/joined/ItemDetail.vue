<template>
  <div class="itemdetail">
    <div id="left">
      <header>
        {{this.item.roomTitle}}
      </header>
      <ul>
        <li>
          스터디장: 
          <span>{{this.item.user.userName}}</span>
        </li>
        <li>
          개설날짜: 
          <span>{{this.item.roomTime}}</span>
        </li>
        <li>
          활동요일: 
          <span>{{this.days}}</span>
        </li>
        <li>
          활동기간: 
          <br>
          <span>{{this.item.roomIngdate}}</span>
        </li>
        <li>
          모집인원: 
          <span>{{this.item.roomPerson}}명</span>
        </li>
      </ul>
    </div>

    <div id="right">
      <p>📌 사용스텍</p>
      <div
        class="imgBox"
      >
        <div
          v-for="(stack, idx) in imgs"
          :key="idx"
          id="imagedatail"
        >
          <img            
            class="mt-2"
            :src="require(`@/assets/stacks/${stack}.png`)"
            alt="stack_logo"
          >
          <p>{{ stack }}</p>
        </div>
      </div>
      <ul>
        <li>
          설명
          <br>
          <div>
            <span v-html="getContent(this.item.roomContent2)"></span>
          </div>
        </li>
        <li>
          오픈카카오톡
          <br>
          <div>
            <span v-html="getContent(this.item.roomContent1)"></span>
          </div>
        </li>
      </ul>
      <footer>
        <button id="applyBtn" @click="goVideo">입장</button>
        <button id="cancelBtn" @click="$emit('close-modal')">닫기</button>
      </footer>
    </div>
    
  </div>
</template>

<script>
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i6b210.p.ssafy.io:5443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
  name: 'ItemDetail',
  props: {
    item: Object,
  },
  data() {
    return {
      imgs: null,
      days: null,

      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: null,
			myUserName: null,

      // password: null,
      // itemid: null,
      // itempassword: null,
    }
  },
  methods:{
    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    goVideo(){
    // OpenVidu 객체 생성 ---
			this.OV = new OpenVidu();

      // 세션 초기화
			this.session = this.OV.initSession();

      // 세션 이벤트가 발생할 때 수행할 작업 지정

      // 수신된 새 스트림마다 subscribers에 추가
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

      // 스트림이 없어지면
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

      // 비동기 오류가 나면
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

      // 유효한 사용자 토큰을 사용하여 세션에 연결

      // getToken: 서버에서 수행할 작업을 시뮬레이션 한 것.
      // token은 백엔드에서 받아와야 한다
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

          // 원하는 속성을 가진 고유한 카메라 스트림 가져오기

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // 오디오 / 마이크 없을 때: undefined
							videoSource: undefined, // 캠. 캠 없을 때: undefined
							publishAudio: true,  	// 시작시 오디오 true/false 여부 
							publishVideo: true,  	// 시작시 캠 true/false 여부
							resolution: '640x360',  // 비디오 해상도
							frameRate: 30,			// 초당프레임
							insertMode: 'APPEND',	// 캠 영상이 video태그에 삽입되는 방법
							mirror: false       	// 거울모드 true/false 여부
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;
            
          // vuex로 stream정보 넘겨줘서 Video에서 꺼내쓰기
          const payload = {
            OV: this.OV,
            session: this.session,
            mainStreamManager: this.mainStreamManager,
            publisher: this.publisher,
            subscribers: this.subscribers,
            mySessionId: this.mySessionId,
            myUserName: this.myUserName,
          }

          this.$store.dispatch('videoInfo', payload)

          // 스트림 게시
          this.session.publish(this.publisher);

					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			// window.addEventListener('beforeunload', this.leaveSession)

      this.$router.push({name:'Video', params:{sessionId: this.item.roomId}})
    },
    getToken (id) {		// id == this.sessionId
			const myToken = this.createSession(id).then(sessionId => this.createToken(sessionId));
			console.log(myToken)    // promise 객체
			return myToken
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
      console.log(sessionId)
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,		// == getToken 으로 들어온 sessionId
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
            console.log(error)
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
  },
  created() {
    // console.log(this.item)
    // this.itemid = this.item.roomId
    // this.itempassword = this.item.roomPwd

    this.mySessionId = (this.item.roomId).toString()
    this.myUserName = this.$store.state.credentials.userName

    let stacks = this.item.roomTechstack
    // 배열로 저장
    let result = stacks.split(',')
    // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
    if(result.length >= 4){
      // console.log(result.slice(0,3))
      this.imgs = result.slice(0,3)
    }else{
      this.imgs = result
    }

    let days = this.item.roomIngday
    let temp = days.split(',')
    for (let i=0; i < temp.length; i++) {
      if (temp[i] === 'mon') {
        temp[i] = '월'
      }
      if (temp[i] === 'tue') {
        temp[i] = '화'
      }
      if (temp[i] === 'wed') {
        temp[i] = '수'
      }
      if (temp[i] === 'thu') {
        temp[i] = '목'
      }
      if (temp[i] === 'fri') {
        temp[i] = '금'
      }
      if (temp[i] === 'sat') {
        temp[i] = '토'
      }
      if (temp[i] === 'sun') {
        temp[i] = '일'
      }
      if (temp[i] === 'yet') {
        temp[i] = '추후 미정'
      }
    }
    this.days = temp.join(',')
  }
}
</script>

<style scoped>
.itemdetail{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#left{
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 100%; 
  margin: 1vh;
}

#left header {
  font-size: 1.8vw;
  margin: 0 auto;
  text-align: center;
  font-weight: bold;
  /* border: 1px solid gray; */
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  /* padding: 0 0 0 1vw; */
  /* width: 90%; */
}

#left ul{
  list-style-type: square;
  margin: 2vh 0 0 0;
}

#left li {
  font-size: 1.3vw;
  font-family: 'Epilogue', sans-serif;
  font-weight: bold;
  padding: 0.3vw;
  margin: 0 0 1vh 0;
}
#left li  span{
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
  font-weight: bold;
}

#pwd{
  border: 1px solid;
  border-radius: .5rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgb(150,150,150);
  /* margin: 1rem 0 1rem 0; */
  font-family: 'Epilogue', sans-serif;
}

#right{
  width: 100%;
  margin: 1.2vh;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

#right p {
  font-size: 1.4vw;
  font-weight: bold;
}

.imgBox{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
#imagedatail{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#right img {
  width: 9.5vh;
  height: 9.5vh;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  padding: 0.7vh 0.5vw 0.7vh 0.5vw;
}

#right ul{
  list-style-type: square;
}

#right li {
  font-size: 1.3vw;
  font-weight: bold;
  padding: 0.3vw;
  margin: 1vh 0 0 0;
}

#right li div{
  font-size: 1rem;
  font-weight: bold;
  padding: 0.3rem;
  margin: 0.5vh 0 0.5vh 0;
}

footer{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

footer button{
  font-size: 1vw;
  font-weight: bold;
  padding: 1vh 1vw 1vh 1vw;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
}

footer button:nth-child(1){
  background-color: #24274A;
  color: white;
}

</style>