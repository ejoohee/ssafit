
<template>
  <div>
    <v-container class="custom-container">
      <v-row class="m-3">
        <div class="centered-buttons">
          <v-col cols="auto">
            <v-btn @click="openConfirmationDialog" color="primary" outlined rounded>
              <v-icon left>mdi-pencil</v-icon> 정보 수정
            </v-btn>
          </v-col>
          <v-col cols="auto">
            <v-btn color="primary" class="button-link" outlined rounded :to="{ name: 'userBoardList', params: { writer: loginUser.nickname } }">
              <v-icon left>mdi-note-text-outline</v-icon>작성한 게시글
            </v-btn>
          </v-col>
          <v-col cols="auto">
            <v-btn color="primary" class="button-link" outlined rounded :to="{ name: 'userLikeList' }">
              <v-icon left>mdi-heart</v-icon> 찜한 영상
            </v-btn>
          </v-col>
        </div>
      </v-row>
    </v-container>

    <!-- 확인 창 모달 -->
    <v-card v-if="showConfirmationDialog" class="confirmation-dialog " outlined width="300px">
      <v-card-title class="confirmation-dialog-title">비밀번호 확인</v-card-title>
      <v-card-text>
        <v-text-field
          type="password"
          v-model="password"
          label="비밀번호"
          outlined
          dense
          @keyup.enter="confirmPassword"
        ></v-text-field>
      </v-card-text>
      <v-card-actions class="confirmation-dialog-actions">
        <v-btn @click="confirmPassword" outlined rounded class="button-link">확인</v-btn>
        &nbsp; &nbsp;
        <v-btn @click="cancelConfirmation" outlined rounded>취소</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: "UserBoard",
    data() {
    return {
      showConfirmationDialog: false,
      password: "",
    };
  },

    computed: {
        ...mapState(["loginUser"])
    },
    methods: {
    // isUser() {
    //   let user = {
    //     password: this.password,
    //   };
    // this.$store.dispatch("isnUser", user);
    // }

      openConfirmationDialog() {
      this.showConfirmationDialog = true; // 확인 창 보이기
    },
    confirmPassword() {
      // 비밀번호 확인 로직 구현
      const userInputPassword = this.password;

      // 비밀번호 일치 여부 확인
      if (userInputPassword === this.loginUser.password) {
        // 비밀번호 일치 시 회원 정보 수정 로직 수행
        this.$router.push({ name: "userDetail", params: { id: this.loginUser.id } });
        this.showConfirmationDialog = false; // 확인 창 닫기
      } else {
        alert('비밀번호가 일치하지 않습니다.');
      }
    },
    cancelConfirmation() {
      this.showConfirmationDialog = false; // 확인 창 닫기
    },
    },
  }

</script>

<style scoped>

.button-link:hover {
  background-color: #81b69d;
}

.button-container {
  margin-top: 10px;
}

 .custom-container {
      max-width: 550px;
      max-height: 500px;
      margin-top: 100px;
      margin-bottom: 30px;
      background-color: #e0eee8;
      padding: 25px;
      border-radius: 10px;
      box-shadow: none;
      border: none;
    }
    .centered-buttons {
  display: flex;
  justify-content: center;
}
.confirmation-dialog {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid #000000;
}

.confirmation-dialog-title {
  font-size: 18px;
  font-weight: bold;
}

.confirmation-dialog-actions {
  justify-content: flex-end;
}
</style>
