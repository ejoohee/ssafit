<template>
  <div>
    <v-container>
      <v-row class="mt-4">
        <v-col cols="10" class="mr-2">
          <!-- 검색기능 구현 -->
          <v-text-field v-model="keyword" @keyup.enter="search" label="검색어 입력" outlined dense></v-text-field>
        </v-col>
        <v-col cols="auto">
          <v-btn color="primary" outlined @click="search" >검색</v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="mt-4">
    <v-card color="rounded-card" outlined flat>
      <v-card-title class="custom-card-title" outlined >
        &nbsp;
        <v-icon  size="29"> mdi-file-document </v-icon>
        &nbsp;
      <v-icon></v-icon>
        <h3 class="custom-heading">Board List</h3>
        <v-divider></v-divider>
      </v-card-title>
      <v-card-text class="custom-card-text">
        <v-data-table :items="boards" :headers="headers" item-key="index" class="elevation-0">
          <template v-slot:item="{ item, index }">
            <tr>
              <td>{{ index + 1 }}</td>
              <td>
                <!-- notUser일 때 requestLogin 클릭 이벤트를 달아준다. -->
                <router-link to="/board/list" @click.native="requestLogin" v-if="notUser" active-class="">
                  {{ item.title }}
                </router-link>
                <!-- notUser가 아니면 게시물 상세페이지로 이동 -->
                <router-link :to="`/board/${item.id}`" v-else>
                  {{ item.title }}
                </router-link>
              </td>
              <td>{{ item.writer }}</td>
              <td>{{ item.viewCnt }}</td>
              <td>{{ item.regDate }}</td>
            </tr>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </div>
  </div>
</template>

  
<script>
import { mapState } from 'vuex';

export default {
  name: 'BoardList',
  data() {
    return {
      mode: 1,
      keyword: '',
      headers: [
        { text: '번호', value: 'index' },
        { text: '제목', value: 'title' },
        { text: '닉네임', value: 'writer' },
        { text: '조회수', value: 'viewCnt' },
        { text: '등록일', value: 'regDate' },
      ],
    };
  },
  computed: {
    ...mapState(['boards', 'loginUser']),
    // 로그인 여부를 확인할 메서드
    notUser() {
      return !this.loginUser;
    },
  },
  // 생성 순간 getBoards요청을 보낸다.
  created() {
    this.$store.dispatch('getBoards');
  },
  methods: {
    requestLogin() {
      alert("게시물 상세보기를 하려면 로그인 해주세요.");
    },
    // 검색을 구현할 메서드
    search() {
      if(this.keyword === "") {
        alert("검색어를 입력해주세요");
      }
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch('getBoards', payload);
    },
  },
};
</script>

<style scoped>

a {
  text-decoration: none;
}
.custom-card-title {
  background-color: #e5ecf1;
  padding: 16px;
  display: flex;
  align-items: center;
}

.custom-heading {
  margin: 0;
  font-size: 24px;
  color: #333;
}

.custom-card-text {
  padding: 8px;
}
.rounded-card {
  border-radius: 8px; /* 원하는 둥글기 크기로 조절 */
  box-shadow: none;
  margin: 30px;
}
</style>