<template>
	<video
		autoplay
		id="video"
	/>
</template>

<script>
export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
};
</script>
<style scoped>
	#video{
		border-radius: 1em;
		width: 25em;
		margin: 1em;
	}
</style>
