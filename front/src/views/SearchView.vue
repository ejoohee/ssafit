<template>
  <div>
    <ssafit-search></ssafit-search>
    <ssafit-search-result></ssafit-search-result>
  </div>
</template>

<script>
import SsafitSearch from '../components/ssafit/SsafitSearch.vue';
import SsafitSearchResult from '../components/ssafit/SsafitSearchResult.vue';

export default {
  name: 'SearchView',
  components: {
    SsafitSearch,
    SsafitSearchResult,
  },
};
</script>

<style scoped>
h2 {
  color: red;
}
</style>