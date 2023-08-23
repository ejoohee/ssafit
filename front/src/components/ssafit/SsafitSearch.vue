<template>
  <div>
  <div>
    <v-container>
      <v-row>
        <v-col cols="10">
          <v-text-field v-model="keyword" label="Search..." outlined placeholder="Search..." @keyup.enter="search"></v-text-field>
        </v-col>
        <v-col cols="auto">
          <v-btn color="primary" outlined @click="search" :style="{ height: '50px' }">검색</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
  </div>
</template>

<script>
import { NavigationDuplicated } from "vue-router";

export default {
  name: 'ExerciseSearch',
  data() {
    return {
      keyword: '',
    };
  },
  methods: {
    search() {
      this.$store.dispatch('searchExercise', this.keyword);
      
      const destination = { name: "search" };
      
      // 현재 경로와 목표 경로가 동일한지 확인합니다.
      if (this.$route.name === destination.name) {
        console.log("이미 해당 경로로 이동 중입니다.");
        return;
      }
      
      try {
        this.$router.push(destination);
      } catch (error) {
        if (error instanceof NavigationDuplicated) {
          console.log("중복 내비게이션 오류가 발생했습니다.");
          // 중복 내비게이션 예외 처리 코드를 작성해주세요.
        } else {
          console.log("내비게이션 오류가 발생했습니다:", error);
          // 다른 내비게이션 오류 처리 코드를 작성해주세요.
        }
      }
    },
  },
};
</script>

<style>
</style>
