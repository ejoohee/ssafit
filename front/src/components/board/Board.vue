<template>
  <div class="Board">
    <!-- BoardNav와 BoardBackground 불러오기 -->
    <board-nav></board-nav>
    <board-background></board-background>
  </div>
</template>

<script>
import BoardNav from '../common/BoardNav.vue';
import BoardBackground from '../common/BoardBackground.vue';
export default {

  name: 'MainBoard',
  components: {
    BoardNav,
    BoardBackground,
    },
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>