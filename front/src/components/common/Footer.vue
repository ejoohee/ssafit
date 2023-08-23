<template>
  <footer class="footer">
    <div class="footer-container">
      <div class="footer-logo">
        <img src="@/assets/exercise.png" alt="사이트 로고"  style="width: 60px; height: 60px; "/>
      </div>
      <div class="footer-info">
        <p><a href="/" class="footer-link">홈</a></p>
        &nbsp;
        <p class="footer-rights">&copy; SSAFIT. 정의석_이주희. All rights reserved.</p>
        <p class="footer-address">주소: 서울특별시 강남구 역삼동 테헤란로 212</p>
        <p class="footer-phone">전화: 1544-9001</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
    name: "FooterView",
}
</script>


<style scoped>
.footer {
  background-color: #f5f5f5;
  padding: 20px;
  text-align: center;
}

.footer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.footer-logo {
  margin-bottom: 20px;
}

.footer-info {
  font-size: 14px;
}

.footer-link {
  text-decoration: none;
  color: #333;
  font-weight: bold;
}

.footer-rights {
  margin-bottom: 10px;
}

.footer-address,
.footer-phone {
  margin: 5px 0;
}
</style>