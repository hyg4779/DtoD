<template>
<div
	v-if="streamManager"
	id="videoBox"
>
	<OvVideo :stream-manager="streamManager"/>
	<article>
		<p class="m-0">{{ clientData }}</p>
		<p class="d-flex m-0">
			<button class="mx-2" @click="videoToggle">{{ myVideoStatus }}</button>
			<button class="mx-2" @click="audioToggle">{{ myAudioStatus }}</button>
		</p>
	</article>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
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
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
		
    // 사용자 비디오 on/off
    videoToggle(){
			console.log(this.streamManager)
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
</style>
