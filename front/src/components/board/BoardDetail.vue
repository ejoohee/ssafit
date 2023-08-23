
<template>
  <v-container class="custom-container">
    <v-card class="m-4" outlined>
      <v-card-title class="blue lighten-1 white--text">
        <h3 class="headline">게시판 상세보기</h3>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12">
            <h4>{{ board.title }}</h4>
            <div>조회수: {{ board.viewCnt }}</div>
            <div>작성자: {{ board.writer }}</div>
            <div>작성일: {{ board.regDate }}</div>
            <div>{{ board.content }}</div>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>

    <br/>
    <!-- getUser가 true일 때 -->
    <div v-if="getUser" class="mt-4"  elevation="0">
      <v-row>
        <v-col cols="12">
          <!-- moveUpdate 클릭 이벤트 달아주기 -->
          <v-btn color="#000000" rounded outlined class="custom-button" @click="moveUpdate">
            <v-icon left>mdi-pencil</v-icon>
            수정
          </v-btn>
          <!-- deleteBoard 클릭 이벤트 달아주기 -->
          <v-btn color="#de6d6d" rounded outlined class="custom-button" @click="deleteBoard">
            <v-icon left>mdi-delete</v-icon>
            삭제
          </v-btn>
          <!-- getUser일 떄는 신고버튼 비활성화(disabled) -->
          <v-btn color="#30751f" rounded outlined disabled class="custom-button">
            <v-icon left>mdi-alert</v-icon>
            신고
          </v-btn> 
        </v-col>
      </v-row>
    </div>

    <div v-else class="mt-4"  elevation="0">
      <v-row>
        <v-col cols="12">
          <!-- notModify 클릭 이벤트 달아주기 -->
          <v-btn color="#000000" rounded outlined class="custom-button" @click="notModify">
           <v-icon left>mdi-pencil</v-icon>
           수정
          </v-btn>
          <!-- notDelete 클릭 이벤트 달아주기 -->
          <v-btn color="#de6d6d" rounded outlined class="custom-button" @click="notDelete">
          <v-icon left>mdi-delete</v-icon>
           삭제
          </v-btn>
          <!-- reportBoard 클릭 이벤트 달아주기 -->
          <v-btn color="#30751f" rounded outlined class="custom-button" @click="reportBoard">
          <v-icon left>mdi-alert</v-icon>
            신고
          </v-btn> 
        </v-col>
      </v-row>
    </div>

    <br/>

    <v-card class="mt-4" outlined>
      <v-card-title class="blue lighten-1 white--text">
        <h3 class="headline">댓글 등록</h3>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field
                readonly
                label="닉네임"
                v-model="boardCommentWriter"
                outlined
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea
                label="내용"
                v-model="boardCommentContent"
                outlined
                rows="3"
                @keyup.enter="createBoardComment"
              ></v-textarea>
            </v-col>
            <v-col cols="12">
              <!-- createBoardComment 이벤트 달아주기 -->
              <v-btn color="#30751f" rounded outlined class="custom-button" @click="createBoardComment">
                <v-icon left>mdi-check</v-icon>
                등록
              </v-btn>
              <v-btn color="#000000" rounded outlined class="custom-button" @click="moveBack">
                <v-icon left>mdi-arrow-left</v-icon>
                뒤로가기
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>

    <br/>

    <v-card class="mt-4" outlined>
      <v-card-title class="blue lighten-1 white--text">
        <h3 class="headline">댓글 목록</h3>
      </v-card-title>
      <v-card-text>
        <!-- v-datatable 이용, headers는 data에서 생성, items는 필터링된 게시물 댓글 배열 사용 -->
        <v-data-table :headers="headers" :items="filteredBoardComments" item-key="index" class="elevation-1">
          <!-- v-slot:item은 v-data-table의 각 항목에 대한 슬롯을 정의 -->
          <template v-slot:item="{ item, index }">
            <tr>
              <td>{{ index + 1 }}</td>
              <td>{{ item.writer }}</td>
              <td>{{ item.content }}</td>
              <td>{{ item.regDate }}</td>
              <td>
                <!-- openMdal과 deleteBoardComment에는 위에서 정의된 item을 전달해야 한다. -->
                <v-btn small outlined color="primary" @click="openModal(item)" class="custom-button">
                  <v-icon left>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn small outlined color="#de6d6d" @click="deleteBoardComment(item)" class="custom-button">
                  <v-icon left>mdi-delete</v-icon>삭제
                </v-btn>
              </td>
            </tr>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>

    <div v-if="isModalOpen" class="modal-container">
      <v-card>
        <v-card-title>
          <span class="headline">댓글 수정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  readonly
                  label="작성자"
                  v-model="this.loginUser.nickname"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                  label="내용"
                  v-model="modalData.content"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions class="d-flex justify-center">
          <!-- updateBoardComment에는 현재 열려있는 modal에 내용을 전달해야하므로 modalData를 전달 -->
          <v-btn color="primary" dark class="custom-button" @click="updateBoardComment(modalData)">
            <v-icon left>mdi-pencil</v-icon>
            수정
          </v-btn>
          <v-btn color="primary" dark class="custom-button" @click="closeModal">
            <v-icon left>mdi-close</v-icon>
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>

  </v-container>
</template>



  <script>
  import { mapState } from 'vuex';
  import moment from 'moment';
  
  export default {
    name: 'BoardDetail',
    computed: {
      // mapState를 이용해서 board, loginUser, boardComments 데이터를 가져온다.
      ...mapState(['board', 'loginUser', 'boardComments']),
      getUser() {
        // 로그인 유저의 닉네임과 게시글 작성자가 같을 때 true, 아닐 때 false 반환
        if (this.loginUser.nickname === this.board.writer) {
          return true;
        } else {
          return false;
        }
      },
      // filteredBoardComments 메서드를 이용해서 게시글의 댓글을 그 댓글에 저장된 게시글 아이디와 현재 게시글 아이디가 같을 때로 필터링
      filteredBoardComments() {
        return this.boardComments.filter(boardComment => boardComment.boardId === this.board.id);
      },
      // boardCommentWriter에 현재 로그인한 유저의 닉네임을 할당
      boardCommentWriter: {
        get() {
          return this.loginUser.nickname;
        },
      },
    },
    data() {
      return {
        boardCommentContent: '',
        // 모달이 열릴 조건(초기 조건은 false)
        isModalOpen: false,
        modalData: { // 모달 창에 표시할 데이터
          id:'',
          content: '',
        },
        // v-data-table에 쓰일 headers
        headers: [
        { text: "번호", value: "index" },
        { text: "글쓴이", value: "writer" },
        { text: "내용", value: "content" },
        { text: "등록일", value: "regDate" },
        { text: "관리", value: "actions" },
      ],

      };
    },
    // 처음 생성될 때 getBoard 요청을 보낸다. (boardId는 URL을 /로 나눴을 때 제일 끝에 있는 값이므로 아래와 같이 구해줄 수 있다.)
    created() {
      const pathName = new URL(document.location).pathname.split('/');
      const boardId = pathName[pathName.length - 1];
      this.$store.dispatch('getBoard', boardId);
    },
    // update될 때마다 getBoardComments 요청을 보내서 댓글 목록을 업데이트 해준다.
    updated() {
      this.$store.dispatch('getBoardComments');
    },
    methods: {
      // 경로 이름이 boardUpdate인 곳으로 푸시
      moveUpdate() {
        this.$router.push({ name: 'boardUpdate' });
      },
      // 게시글 삭제 메서드 (confirm으로 한번 확인한 뒤 '네'를 누르면 delteBoard요청을 보낸다.)
      // 반드시 게시글 아이디를 보내줘야 삭제가 가능하다.
      deleteBoard() {
        if(confirm("정말로 삭제하시겠습니까?")) {
          this.$store.dispatch('deleteBoard', this.board.id);
        }
      },
      // 게시글 신고 메서드 (confirm으로 확인 후 addReportBoard 요청을 보낸다.)
      // 반드시 게시글의 아이디와 유저의 아이디 정보를 같이 보내줘야 한다.
      // 그래야 한 번 신고한 게시글을 같은 유저가 다시 신고하지 못한다.
      reportBoard() {
        
        if (confirm('해당 게시물을 신고하시겠습니까?')) {
          this.$store.dispatch('addReportBoard', {boardId : this.board.id, userId : this.loginUser.id});
        }
      },
      // alert창을 띄워줄 메서드들
      notModify() {
        alert("수정할 수 없는 게시물입니다.");
      },
      notDelete() {
        alert("삭제할 수 없는 게시물입니다.");
      },
      moveBack() {
        this.$router.push({name: "boardList"});
      },
      // 댓글 등록을 위한 메서드
      createBoardComment() {
        // 댓글 변수 생성 (게시글 아이디, 작성자, 내용, 작성일 정보 담기)
        let boardComment = {
          boardId: this.board.id,
          writer: this.boardCommentWriter,
          content: this.boardCommentContent,
          regDate: moment().format('YYYY-MM-DD HH:mm:ss'),
        };
        // 만약 내용을 입력 안했으면 alert창 띄우기
        if (
          boardComment.content === ""
        ) {
          alert("내용을 입력해주세요");
          return;
        }
        // 정상적으로 입력되면 createBoardComment 요청을 작성한 댓글 정보와 함께 보내기
        this.$store.dispatch('createBoardComment', boardComment)
        // 요청을 정상적으로 마치면 댓글 등록창의 내용 부분을 다시 비워준다. 
        this.boardCommentContent = '';
          
          //중복 해결 (현재 경로와 타겟경로를 비교해서 다르면 타겟경로로 보내준다.)
          const currentPath = this.$router.currentRoute.path;
          const targetPath = '/board/:id';
          
          if (currentPath !== targetPath) {
            this.$router.push(targetPath);
          }
          
        },
        // 모달창을 열지 결정할 메서드
        openModal(boardComment) {
          // 로그인 유저와 작성자의 닉네임이 같아야 모달창을 열 조건을 true로 바꿔준다.
          if(this.loginUser.nickname === boardComment.writer) {
            this.isModalOpen = true;
            this.modalData.content = boardComment.content;
            this.modalData.id = boardComment.id;
          } else {
            // 그렇지 않으면 alert창만 띄워준다.
            alert("수정할 수 없는 댓글입니다.")
          }
        },
        // 모달창을 닫을 메서드
        closeModal() {
          this.isModalOpen = false;
        },
        // 댓글을 수정할 메서드
        updateBoardComment(boardComment) {
          console.log("댓글 내용, 모달데이터 내용");
          console.log(this.modalData.content);
          // 댓글 내용과 댓글 아이디를 저장
          boardComment.content = this.modalData.content;
          boardComment.id = this.modalData.id;
          console.log(boardComment.id);
          console.log(boardComment.content);
          // updateBoardComment 요청 보내기 (게시글 아이디와 댓글 정보를 같이 보낸다.)
          this.$store.dispatch('updateBoardComment', {boardId : this.board.id, boardComment})
          .then(() => {
            this.closeModal(); // 비동기 작업이 완료된 후 closeModal 이벤트 호출
          });
        },

        // 댓글 삭제 메서드
        deleteBoardComment(boardComment) {
          // 로그인 유저와 작성자가 같아야 삭제 가능
          if(this.loginUser.nickname === boardComment.writer) {
            if(confirm("정말로 삭제하시겠습니까?")){
              // 삭제할 때 게시글 아이디와 댓글 아이디를 같이 보내준다.
            this.$store.dispatch('deleteBoardComment', {boardId : this.board.id, boardCommentId : boardComment.id})
          }
        } else {
          alert("삭제할 수 없는 댓글입니다.")
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .custom-button{
    margin: 10px;
  }
  </style>
  