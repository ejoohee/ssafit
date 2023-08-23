<template>
  <div class = "container">
    <v-card class="registration-card" outlined color="black">
      <v-card-title>
        <h2> LOGIN</h2>
      </v-card-title>
      <v-card-text>
        <v-form @submit.prevent="login">
          <v-container>
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
                <v-btn type="submit" class="btn" outlined  rounded > <v-icon left>mdi-login</v-icon> 로그인</v-btn>
                &nbsp;
                <v-btn @click="moveSSAFIT" outlined  rounded><v-icon left>mdi-close</v-icon> 취소</v-btn>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>


<script>
export default {
  name: "LoginForm",
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    login() {
      let user = {
        id: this.id,
        password: this.password,
      };

      this.$store.dispatch("setLoginUser", user);
    },
    moveSSAFIT() {
      this.$router.push({name : 'ssafit'});
    },
  },
};
</script>
<style scoped>


.registration-card {
  max-width: 450px;
  width: 100%;
  padding: 2rem;
  margin: 0 auto;
  border: 1px solid rgb(109, 110, 112);
}

.btn {
  color: rgb(31, 29, 29);
}



</style>
