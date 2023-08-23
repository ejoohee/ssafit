<template>
    <v-card @click="clickVideo" class="custom-card">
      <v-img :src="video.snippet.thumbnails.default.url"></v-img>
      <v-card-text>{{ video.snippet.title }}</v-card-text>
    </v-card>
  </template>
  
  
  <script>
  export default {
    name: 'UserLikeItem',
    props: {
      video: {
        type: Object,
        required: true,
      },
    },
    methods: {
      clickVideo() {
        const videoId = this.video.id.videoId;
        this.$store.dispatch('clickVideo', this.video);
        this.$router.push({ name: "video" , params: { videoId }});
      },
    },
  };
  </script>
  
  <style>
  .custom-card {
    cursor: pointer;
    max-width: 300px;
    margin-bottom: 16px;
  }
  </style>