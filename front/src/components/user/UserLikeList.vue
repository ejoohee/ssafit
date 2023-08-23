
<template>
  <div>
    <v-container  class="custom-container">
    <v-card outlined>
    <v-card-title>
      <h3>찜 목록</h3>
      <hr>
    </v-card-title>
    <v-card-text>
      <v-data-table :headers="headers" :items="filteredMyVideos" item-key="index" class="elevation-1">
        <template v-slot:item="{ item, index }">
          <tr>
            <td>{{ index + 1 }}</td>

            <td>
              <a :href="`https://www.youtube.com/embed/${item.videoId}`" target="_blank">
                {{ item.videoTitle }}
              </a>
            </td>
            <td>{{ item.id }}</td>
          </tr>
        </template>
      </v-data-table>
      <v-btn @click="moveMypage" outlined rounded>
        <v-icon left>mdi-arrow-left</v-icon>
        뒤로가기
      </v-btn>
    </v-card-text>
    </v-card>
    </v-container>

  </div>
  </template>




<script>
  import { mapState } from "vuex";

export default {
  name: 'UserLikeList',

  data() {
        return {
          isLiked: false,
          headers: [
          { text: '번호', value: 'index' },
          { text: '제목', value: 'title' },
          { text: '닉네임', value: 'writer' },
          ],
        }
    },

  created() {
    // const pathName = new URL(document.location).pathname.split('/');
    // const videoId = pathName[pathName.length - 1];
    // this.$store.dispatch('getVideo', videoId);
  },

  updated() {
    this.$store.dispatch('getMyVideos');
  },

  methods: {
     moveMypage() {
        this.$router.push({ name: 'userBoard', params: { id: this.loginUser.id } });
      },
      clickVideo() {
      const videoId = this.video.id.videoId;
      this.$store.dispatch('clickVideo', this.video);
      this.$router.push({ name: "video" , params: { videoId }});
    },
  },
  computed: {
    ...mapState(['loginUser','myVideos', 'myVideo']),

    filteredMyVideos() {

      return this.myVideos.filter(
        myVideo => myVideo.id === this.loginUser.id);
    },

    videoURL() {
      return `https://www.youtube.com/embed/${this.video.id.videoId}`;
    },
    buttonColor() {
    return this.isLiked ? 'red' : 'secondary';
    },
  },
};
</script>

<style scoped>

.custom-container {
      margin-top: 30px;
      margin-bottom: 30px;
      background-color: #f2f2f2;
      padding: 20px;
      border-radius: 10px;
      box-shadow: none;
      border: none;
}
</style> 