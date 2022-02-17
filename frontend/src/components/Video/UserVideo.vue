<template>
<div
	v-if="streamManager"
	id="videoBox"
>
	<OvVideo :stream-manager="streamManager"/>
	<article>
		<p class="m-0">{{ clientData }}</p>
		<p class="d-flex m-0">
			<button 
				:class="{active: isActive}"
				@click="videoToggle"
			>
				{{ myVideoStatus }}
			</button>

			<button 
				:class="{active: isActive}"
				@click="audioToggle"
			>
				{{ myAudioStatus }}
			</button>
		</p>
	</article>
</div>
</template>

<script>
import OvVideo from './OvVideo';
import { mapState } from 'vuex'
export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		...mapState([
			'credentials',
		]),
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
		myVideoStatus(){
      let video = this.streamManager.stream.videoActive
      if(video){
        return '🖥'
      }return '🖥❌'
    },
    myAudioStatus(){
      let audio = this.streamManager.stream.audioActive
      if(audio){
        return '🔊'
      }return '🔈❌'
    },
		isActive(){
			if(this.clientData === this.credentials.userName){
				return true
			}return false
		}
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
		
    // 사용자 비디오 on/off
    videoToggle(){
			const video = this.streamManager.stream.videoActive
      if(video){
        this.streamManager.publishVideo(false)
        console.log('video ' + video)
      }else{
        this.streamManager.publishVideo(true)
        console.log('video ' + video)
        }
    },

    // 사용자 오디오 on/off
    audioToggle(){
      console.log(this.subscribers)
      const audio = this.streamManager.stream.audioActive
      if(audio){
        this.streamManager.publishAudio(false)
        console.log('audio ' + audio)
      }else{
        this.streamManager.publishAudio(true)
        console.log('audio ' + audio)
      }
    },
	},
};
</script>
<style scoped>
#videoBox{
	display: flex;
	flex-direction: column;
	align-items: center;
	font-weight: bolder;
}

article{
	display: flex;
	flex-direction: column;
	align-items: center;
}
article p button{
	margin: 0 .5vw 0 .5vw;
}

.active{
	cursor: pointer;
}
</style>
