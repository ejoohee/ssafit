<template>
  <div>
    <v-container  class="custom-container">
    <v-card outlined>
    <v-card-title>
      <h3>작성한 게시물</h3>
      <hr>
    </v-card-title>
    <v-card-text>
      <v-data-table :items="userBoards" :headers="headers" item-key="index">
        <template v-slot:item="{ item, index }">
          <tr>
            <td>{{ index + 1 }}</td>
            <td>
              <router-link to="/board/list">
                {{ item.title }}
              </router-link>
              <router-link :to="`/board/${item.id}`">
                {{ item.title }}
              </router-link>
            </td>
            <td>{{ item.writer }}</td>
            <td>{{ item.viewCnt }}</td>
            <td>{{ item.regDate }}</td>
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
import { mapState } from 'vuex';

export default {
  name: 'UserBoardList',
  data() {
      return {
        writer: '',
        headers: [
        { text: '번호', value: 'index' },
        { text: '제목', value: 'title' },
        { text: '닉네임', value: 'writer' },
        { text: '조회수', value: 'viewCnt' },
        { text: '등록일', value: 'regDate' },
        ],
      }
  },
  computed: {
    ...mapState(['userBoards', 'loginUser']),
  },
  created() {
    this.writer = this.loginUser.nickname;
    this.$store.dispatch('getUserBoards', this.writer);
  },
  methods: {
    moveMypage() {
      this.$router.push({ name: 'userBoard', params: { id: this.loginUser.id } });
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