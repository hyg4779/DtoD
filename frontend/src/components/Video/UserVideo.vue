<template>
<div
	v-if="streamManager"
	id="videoBox"
>
	<OvVideo :stream-manager="streamManager"/>
	<div>
		<p class="m-0">{{ clientData }}</p>
	</div>
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
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
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
</style>
