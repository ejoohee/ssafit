  <template>
    <v-container  class="custom-container">
      <h3>게시판 등록</h3>
      <br/>
      <v-card outlined>
        <v-card-title class="headline">
          <span>등록</span>
        </v-card-title>
        <v-card-text>
          <v-form @submit="createBoard">
            <v-text-field
              v-model="title"
              label="제목"
              outlined
              required
            ></v-text-field>
            <v-text-field
              v-model="writer"
              label="닉네임"
              outlined
              readonly
            ></v-text-field>
            <v-textarea
              v-model="content"
              label="내용"
              outlined
              required
            ></v-textarea>
            <v-btn color="#30751f" rounded outlined class="custom-button" @click="createBoard">
              <v-icon left>mdi-check</v-icon>
              등록
            </v-btn>
            <v-btn color="#000000" rounded outlined class="custom-button" @click="moveBack">
              <v-icon left>mdi-arrow-left</v-icon>
              뒤로가기
            </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-container>
  </template>
    
    <script>
    import { mapState } from "vuex";
    export default {
      name: 'BoardCreate',
      data() {
        return {
          title: '',
          content: '',
        };
      },
      computed: {
        ...mapState(["loginUser"]),
        // writer를 loginUser의 닉네임으로 받아오기 
        writer: {
          get() {
            return this.loginUser.nickname;
          },
        },
      },
      methods: {
        createBoard() {
          // 만약 제목이나 내용이 비어있으면 alert창 띄우기
          if (
            this.title === "" ||
            this.content === ""
        ) {
          alert("모든 내용을 입력해주세요");
          return;
        }
        // board 객체 생성 (id, 제목, 작성자, 내용)
          let board = {
            id: 0,
            title: this.title,
            writer: this.writer,
            content: this.content,
          };
          // store에 있는 createBoard로 board와 함께 쏴주자.
          this.$store.dispatch('createBoard', board);
        },
        moveBack() {
          this.$router.push({name: "board"});
        },
      },
    };
    </script>
    
    <style>
    .custom-container {
        margin-bottom: 30px;
        padding: 20px;
        border-radius: 10px;
        box-shadow: none;
        border: 2px solid;
      }
    .custom-button{
      margin: 10px;
    }
  </style>
    
  