<template>
  <div id="app">
    <header-nav />
    <router-view/>
    <footer-view/>
  </div>
</template>

<script>

import HeaderNav from "@/components/common/HeaderNav.vue";
import FooterView from "@/components/common/Footer.vue";

export default {
  name: "App",
  components: {
    HeaderNav,
    FooterView,
  },
  computed: {
   
  },
};
</script>

<style>
#app {
  font-family: 'Gowun Dodum', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh; /* 최소 높이를 화면 전체 높이로 설정 */
  display: flex; /* Flex 레이아웃 사용 */
  flex-direction: column; /* 요소들을 세로로 정렬 */
}

.router-view {
  flex: 1; /* Flex 아이템이 남은 공간을 차지하도록 설정 */
}

footer {
  margin-top: auto; /* 나머지 공간을 위로 마진으로 채우고 바닥에 붙임 */
}

.footer-info a.router-link-exact-active {
  color: #42b983; /* 활성 링크의 색상 설정 */
}

.footer-info a[href="/"] {
  color: #254fb8; /* 홈 링크의 색상 설정 */
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
