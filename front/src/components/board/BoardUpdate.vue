  <template>
    <v-container>
      <v-card class="registration-card">
        <v-card-title>
          <h3>게시글 수정</h3>
        </v-card-title>
        <v-card-text>
          <v-form>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="제목"
                  v-model="board.title"
                  @keyup.enter="updateBoard"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="쓴이"
                  readonly
                  v-model="board.writer"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  label="내용"
                  v-model="board.content"
                ></v-textarea>
              </v-col>
              <v-col cols="12">
                <v-btn color="#000000" rounded outlined @click="updateBoard" class="custom-button">
                  <v-icon left>mdi-pencil</v-icon>
                  수정
                </v-btn>
                <v-btn color="#000000" rounded outlined @click="moveBoardDetail" class="custom-button">
                  <v-icon left>mdi-arrow-left</v-icon>
                  취소
                </v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
      </v-card>
    </v-container>
  </template>

  <script>
  import { mapState } from 'vuex';
  
  export default {
    name: 'BoardUpdate',
    computed: {
      ...mapState(['board']),
    },
    methods: {
      // 게시물을 수정할 메서드
      updateBoard() {
        // 수정 게시물을 담을 객체 생성
        let updateBoard = {
          id: this.board.id,
          title: this.board.title,
          content: this.board.content,
        };
        // 정보를 담아서 updateBoard 요청
        this.$store.dispatch('updateBoard', updateBoard);
        // 요청이 완료되면 수정완료 alert창 띄우기
        alert("수정되었습니다.")
      },
      // 게시물 상세피이지로 이동할 메서드
      moveBoardDetail() {
        this.$router.push(`/board/${this.board.id}`);
      },
    },
  };
  </script>
  
<style scoped>
.registration-card {
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem;
}
.custom-button{
    margin: 10px;
  }
</style>
  