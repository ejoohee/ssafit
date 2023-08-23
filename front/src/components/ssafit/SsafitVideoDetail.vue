<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
        <div class="text-center" style="margin: 1rem;">
          <h3>{{ video.snippet.title }}</h3>
        </div>
      </v-col>
      <v-col cols="12">
        <div class="text-center">
          <iframe
            width="560"
            height="315"
            :src="videoURL"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen
          ></iframe>
        </div>
      </v-col>
    </v-row>

    <br/>
    <v-btn :style="{ color: isLiked ? 'red' : 'inherit' } " rounded outlined @click="toggleLike">
      <v-icon left>mdi-heart</v-icon>
      찜
    </v-btn>
    <br/>
    <br/>

    <v-card class="mt-4 black--text" outlined tile>
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
                v-model="loginUser.nickname"
                outlined
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea
                label="내용"
                v-model="content"
                outlined
                rows="3"
                @keyup.enter="createVideoComment"
              ></v-textarea>
            </v-col>
            <v-col cols="12">
              <v-btn color="#30751f" rounded outlined class="custom-button" @click="createVideoComment">
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
    <div>
      <v-data-table :headers="headers" :items="filteredComments" item-key="index">
        <template v-slot:item="{ item, index }">
          <tr>
            <td>{{ index + 1 }}</td>
            <td>{{ item.nickname }}</td>
            <td>{{ item.content }}</td>
            <td>{{ item.regDate }}</td>
            <td>
              <v-btn small outlined color="primary" @click="openModal(item)">
                <v-icon left>mdi-pencil</v-icon>수정
                </v-btn>
                &nbsp;
                &nbsp;
                <v-btn small outlined color="#de6d6d" @click="deleteVideoComment(item)">
                <v-icon left>mdi-delete</v-icon>삭제
              </v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
    </div>

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
                  v-model="modalData.nickname"
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
          &nbsp; &nbsp; 
          <v-btn color="primary" dark class="mr-4" @click="updateVideoComment(modalData)">
          <v-icon left>mdi-pencil</v-icon>
            수정
          </v-btn>
          &nbsp; &nbsp; &nbsp;
          <v-btn color="primary" dark @click="closeModal">
            <v-icon left>mdi-close</v-icon>
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </v-container>
</template>

<script>
// import { mapGetters } from 'vuex';
import { mapState } from 'vuex';
import moment from 'moment';
// import axios from 'axios'
// import VideoCommentList from '../videoComment/VideoCommentList.vue';
// import VideoCommentWrite from '../videoComment/VideoCommentWrite.vue';

export default {
  name: 'SsafitVideoDetail',

  data() {
    return {
      isLiked: false,
      content: '',
      isModalOpen: false,
      modalData: {
        // 모달 창에 표시할 데이터
        content: '',
        commentSeq: '',
        nickname: '',
      },
      headers: [
        { text: "번호", value: "index" },
        { text: "글쓴이", value: "nickname" },
        { text: "내용", value: "content" },
        { text: "등록일", value: "regDate" },
        { text: "관리", value: "actions" },
      ],
    };
  },
  created() {
    // const pathName = new URL(document.location).pathname.split('/');
    // const videoId = pathName[pathName.length - 1];
    // this.$store.dispatch('getVideo', videoId);
  },

  updated() {
    this.$store.dispatch('getVideoComments');
  },

  methods: {
    openModal(videoComment) {
      if (this.loginUser.nickname === videoComment.nickname) {
        // console.log('들어왔니?');
        this.isModalOpen = true;
        this.modalData = {
          content: videoComment.content,
          commentSeq: videoComment.commentSeq,
          nickname: videoComment.nickname,
        };
      } else {
        alert('수정할 수 없는 댓글입니다.');
      }

      console.log(videoComment);
    },
    closeModal() {
      this.isModalOpen = false;
    },
    moveBack() {
      this.$router.push({name: "search"});
    },

    createVideoComment() {
      // eslint-disable-next-line no-unused-vars
      const videoId = this.video.id.videoId;

      let videoComment = {
        videoId: videoId,
        nickname: this.loginUser.nickname,
        content: this.content,
        regDate: moment().format('YYYY-MM-DD HH:mm:ss'),
      };
      if (this.content === '') {
        alert('내용을 입력해주세요');
        return;
      }
      this.$store.dispatch('createVideoComment', videoComment);

      this.content = '';

      const currentPath = this.$router.currentRoute.path;
      const targetPath = `/${this.video.id.videoId}`;
      if (currentPath !== targetPath) {
        this.$router.push(targetPath);
      }
    },
    updateVideoComment(videoComment) {
      if (!confirm('댓글을 수정하시겠습니까?')) {
        return;
      }
      // const nickname = this.modalData.nickname;
      // const content = this.modalData.content;
      console.log(this.modalData.content);
      videoComment.content = this.modalData.content;
      videoComment.commentSeq = this.modalData.commentSeq;
      videoComment.nickname = this.modalData.nickname;

      console.log(videoComment);
      console.log(this.modalData);

      this.$store.dispatch('updateVideoComment', {
        videoId: this.video.id.videoId,
        videoComment,
      }).then(() => {
        this.closeModal(); // 비동기 작업이 완료된 후 closeModal 이벤트 호출
      });
    },
    deleteVideoComment(videoComment) {
      if(this.loginUser.nickname === videoComment.nickname) {
        if (confirm('정말로 삭제하시겠습니까?')) {
          console.log(videoComment);
  
          this.$store
            .dispatch('deleteVideoComment', {
              videoId: this.video.id.videoId,
              videoCommentId: videoComment.commentSeq,
            })
        }
      } else {
        alert("삭제할 수 없는 댓글입니다.")
      }
    },

    toggleLike() {
  if (!this.isLiked && confirm('해당 비디오를 찜하시겠습니까?')) {
    this.isLiked = true;

    const videoId = this.video.id.videoId;

      let myVideo = {
        videoId: videoId,
        id: this.loginUser.id,
        videoTitle: this.video.snippet.title,
      };

      console.log(myVideo);
      this.$store.dispatch('addMyVideo', myVideo);

      const currentPath = this.$router.currentRoute.path;
      const targetPath = `/${this.video.id.videoId}`;
      if (currentPath !== targetPath) {
        this.$router.push(targetPath);
      }
  } else if (this.isLiked && confirm('찜을 취소하시겠습니까?')) {
    this.isLiked = false;
  }
}
  },

  components: {
    // VideoCommentList,
    // VideoCommentWrite,
  },
  computed: {
    ...mapState(['video', 'loginUser', 'videoComments', 'videoComment', 'myVideo']),
    filteredComments() {
      // videoComments 배열을 videoId에 해당하는 댓글만 필터링하여 반환합니다.
      return this.videoComments.filter(
        (videoComment) => videoComment.videoId === this.video.id.videoId
      );
    },
    getUser() {
      if (this.loginUser.nickname === this.videoComment.nickname) {
        return true;
      } else {
        return false;
      }
    },
    isUser() {
      return this.loginUser;
    },
    videoURL() {
      return `https://www.youtube.com/embed/${this.video.id.videoId}`;
    },
  },
};
</script>

<style>
.modal-container {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
}
.custom-button{
    margin: 10px;
}
</style>
