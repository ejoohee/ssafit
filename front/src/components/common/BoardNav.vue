<template>
    <header>
      <nav>
        <!-- 로그인 되어있지 않으면 로그인 요청-->
        <router-link to = "/board" @click.native="requestLogin" v-if="notUser">게시글 등록</router-link>
        <router-link :to="{ name: 'boardCreate' }" v-else>게시글 등록</router-link> |
        <router-link :to="{ name: 'boardList' }">게시글 목록</router-link>
      </nav>
    </header>
</template>
  
  <script>
  import { mapState } from "vuex";
  export default {
    name: "BoardNav",
    methods: {
      requestLogin() {
        alert("로그인 해주세요.");
      }
    },
    computed: {
      ...mapState(["loginUser"]),
      notUser() {
        return !this.loginUser;
      },
    },
  };
  </script>
  
  <style scoped>
  nav {
    padding: 30px;
  }

  a {
  text-decoration: none;
}
  
  nav a {
    font-weight: bold;
    color: #2c3e50;
  }
  
  nav a.router-link-exact-active {
    color: #42b983;
  }
  </style>
  