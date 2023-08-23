<template>
  <div class="board">
    <board-main></board-main>
    <router-view />
  </div>
</template>

<script>
// @ is an alias to /src
import BoardMain from '@/components/board/BoardMain.vue';

export default {
  name: 'BoardView',
  components: {
    BoardMain,
  }
}
</script>
