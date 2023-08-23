<template>
  <div>
    <ssafit-search></ssafit-search>
    <ssafit-video-detail></ssafit-video-detail>
  </div>
</template>

<script>
import SsafitSearch from '../components/ssafit/SsafitSearch.vue';
import SsafitVideoDetail from '../components/ssafit/SsafitVideoDetail.vue';


export default {
name: 'VideoView',
components: {
  SsafitSearch,
  SsafitVideoDetail,
},

// props: {
//   videoId: {
//     type: String,
//     required: true
//   }
// },
};
</script>

<style scoped>
h2 {
color: red;
}
</style>
