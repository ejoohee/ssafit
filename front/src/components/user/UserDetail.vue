<template>
  <v-container  class="custom-container">
    <h2>MY PAGE</h2>
    <br/>
    <v-card class="registration-card" outlined color="black">
      <v-card-text>
        <v-row>
          <v-col cols="12">
            <v-text-field
              readonly
              label="아이디"
              v-model="user.id"
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              type="password"
              label="비밀번호"
              v-model="user.password"
              outlined
              @keyup.enter="updateUser"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              readonly
              label="닉네임"
              v-model="user.nickname"
              outlined
              @keyup.enter="updateUser"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              readonly
              label="이메일"
              v-model="user.email"
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              type="number"
              label="나이"
              v-model="user.age"
              outlined
              @keyup.enter="updateUser"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <!-- color="#30751f" -->
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <v-btn color="primary" @click="updateUser" rounded outlined>정보수정</v-btn>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <v-btn color="#de6d6d" @click="deleteUser" rounded outlined>회원탈퇴</v-btn>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <v-btn @click="moveMypage" rounded outlined>뒤로가기</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
  
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "UserDetail",
  methods: {
    updateUser() {
      this.$store.dispatch("updateUser", this.user)
      
    },
    deleteUser() {
      if(confirm("정말로 탈퇴하시겠습니까?")) {
        this.$store.dispatch("deleteUser", this.user.id);
      }
    },
    async setUser(id) {
     await this.$store.dispatch("setUser", id);
    },
    moveMypage() {
      this.$router.push({ name: 'userBoard', params: { id: this.user.id } });
    },
  },
  computed: {
    ...mapState(["user"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    // detail이 pathName에 추가로 붙었으니,, -2로 바꿔준다.
    const id = pathName[pathName.length - 2];
    this.setUser(id);
  },
};
</script>

<style scoped>
.registration-card {
  max-width: 500px;
  margin: 0 auto;
  padding: 2rem;
}

.custom-container {
      margin-top: 30px;
      margin-bottom: 30px;
      max-width: 500px;
      padding: 20px;
      border-radius: 10px;
      box-shadow: none;
      border: 2px solid;
    }
</style>
