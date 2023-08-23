<template>
  <header class ="header" color="transparent">
    <nav class="header-nav" style="display: flex; justify-content: space-between; align-items: center;">
      <div class="header-nav-left" style="display: flex; align-items: center;">
        <div>
          <a href="/"><img src="@/assets/발빠짐주의3.jpg" style="width: 50px; height: 60px; border-radius: 10px ; border: 3px solid #fff; margin-left: 10px;"></a>
        </div>
        &nbsp;
        <div>
          <router-link to="/" class="header-nav-link" style="font-size: 22px;  text-decoration: none;">SSAFIT</router-link>
        </div>
        &nbsp;
        <div>
          <router-link to="/board" class="header-nav-link" style="font-size: 22px;  text-decoration: none;">BOARD</router-link>
        </div>
      </div>
      <div v-if="getUser" class="header-nav-user">
          <div class="user-welcome m-4" style=" font-family: serif;">

            <v-icon class="mr-1" size="30">mdi-account-circle-outline</v-icon>
            &nbsp;
            <span class="user-nickname" style="font-weight: bold" >{{ loginUser.nickname }}</span>님 환영합니다.
          </div>
      </div>
      <div class="header-nav-right" style="display: flex; align-items: center;">
        <div v-if="getUser" class="header-nav-user">
          <div class="nav-links">
            <router-link to="/" @click.native="logout" class="header-nav-link">
              <v-icon class="mr-1">mdi-logout</v-icon>
              로그아웃
            </router-link>
            <router-link :to="{ name: 'userBoard', params: { id: loginUser.id } }" class="header-nav-link" >
              <v-icon class="mr-1">mdi-account</v-icon>
              마이페이지
            </router-link>
          </div>
        </div>
        <div v-else>
          <div class="nav-links">
            <router-link :to="{ name: 'login' }" class="header-nav-link">
              <v-icon class="mr-1">mdi-login</v-icon>
              로그인
            </router-link>
            <router-link :to="{ name: 'userRegist' }" class="header-nav-link">
              <v-icon class="mr-1">mdi-account-plus</v-icon>
              회원가입
            </router-link>
          </div>
        </div>
      </div>
    </nav>  
  </header>
</template>

  <script>
  import { mapState } from "vuex";
  export default {
    name: "HeaderNav",
    methods: {
      logout() {
        this.$store.commit("LOGOUT");
      },
    },
    computed: {
      ...mapState(["loginUser"]),
      getUser() {
        if (this.loginUser) {
          return true;
        } else {
          return false;
        }
      },
    },
  };
  </script>

  
<style scoped>
  a {
    text-decoration: none;
  }

  .headline {
  font-size: 22px;
  font-weight: bold;
}
  .header-nav {
    align-items: center;
    /* background-color: rgb(234, 239, 245); */
    background-color: #f5f5f5;
    height: 85px; /* 원하는 높이로 조절 */
  }

  .header-nav-right {
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }
  
  .header-nav-user {
    margin-right: 1rem;
  }
  
  .header-nav-link {
    margin-left: 1rem;
    text-decoration: none;
  }

  </style>


