
<template>
  <div>
    <v-card>
      <v-card-title class="text-center">
        <h2 class="mb-4"> &nbsp;VIDEO</h2>
      </v-card-title>
      <v-row align="start" class="custom-list-row">
        <v-col cols="4" md="8" lg="3" v-for="video in videos" :key="video.id.videoId" class="custom-list-col">
          <ssafit-video-item :video="video"></ssafit-video-item>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>



<script>
import { mapState } from 'vuex';
import SsafitVideoItem from './SsafitVideoItem.vue';

export default {
  components: { SsafitVideoItem },
  name: 'SsafitSearchResult',
  computed: {
    ...mapState(['videos']),
  },
};
</script>

<style scoped>
/* .ssafit-list {
  text-align: center;
} */

/* .custom-list-item .v-list-item__title::before {
  content: none;
} */

.custom-card {
  margin: 20px;
  width: calc(100% - 40px);
}

.custom-list-row {
  flex-wrap: wrap;
  margin: -20px;
}

.custom-list-col {
  padding-right: 8px;
  padding: 20px;
}

</style>
