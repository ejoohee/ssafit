<template>
  <div class="container">
    <h2>회원 가입</h2>
    <v-card class="registration-card">
      <v-card-text>
        <v-form @submit="regist">
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="id"
                label="아이디"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="password"
                label="비밀번호"
                type="password"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="nickname"
                label="닉네임"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="email"
                label="이메일"
                required
                type="email"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="age"
                label="나이"
                type="number"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" class="text-center">
              <v-btn type="submit">등록</v-btn>
              <v-btn @click="home">취소</v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "UserList",
  data() {
    return {
      id: "",
      password: "",
      nickname: "",
      email: "",
      age: 0,
    };
  },
  methods: {
    regist() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.nickname === "" ||
        this.email === "" ||
        this.age === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        id: this.id,
        password: this.password,
        nickname: this.nickname,
        email: this.email,
        age: this.age,
      };

      this.$store.dispatch("createUser", user);
    },
    home() {
      this.$router.push('/');
    },
  },
  computed: {
  },
};
</script>

<style scoped>
.registration-card {
  max-width: 500px;
  margin: 0 auto;
  padding: 2rem;
}
</style>
