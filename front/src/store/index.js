import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from "../router";

//데이터를 로컬스토리지에 저장하고 웹 페이지를 새로고침하거나 브라우저를 다시 시작할 때
//해당 데이터를 복원하여 사용할 수 있도록 도와줌
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

const REST_API = `http://localhost:9999`

export default new Vuex.Store({
  plugins: [createPersistedState()],
  //애플리케이션 전체에서 사용되는 데이터를 저장하는 객체이다.

  state: {
    videos: [],
    video: null,

    myVideo: {},
    myVideos: [],

    videoComment: {},
    videoComments: [], 

    users: [],
    user: {},
    loginUser: null,

    boards: [], // 게시물 목록
    board: {}, // 게시물
    userBoards: [], // 작성자의 게시물 목록
    updateBoards: [], // 수정된 게시물 목록
    
    boardComments: [], // 댓글 목록
    boardComment: {}, // 댓글
    selectComments: [], // 게시글 별 댓글 목록

    reports: [], // 신고 누적 정보
    reportCnt: 3, // 게시물 삭제를 위한 누적 신고값 (3회 신고되면 게시글 삭제)
    reportedReports: [], // 이미 신고된 게시물과 유저 정보를 저장하는 배열

  },
  getters: {

  },

  mutations: {
    SEARCH_EXERCISE(state, videos){
      state.videos = videos
    },
    CLICK_VIDEO(state, video){
      state.video = video
    },

    
    CREATE_VIDEO_COMMENT(state, videoComment){
      console.log(Array.isArray(state.videoComments));
      state.videoComments.push(videoComment);
    },
    GET_VIDEO_COMMENTS(state, videoComments){
      state.videoComments = videoComments
    },
    // GET_VIDEO_COMMENT(state, videoComment){
    //   state.videoComment = videoComment
    // },
    UPDATE_VIDEO_COMMENT(state, videoComment){
      state.videoComment = videoComment
    },


    CREATE_USER(state, user) {
      state.users.push(user);
    },
    SET_USERS(state, users) {
      state.users = users;
    },
    SET_USER(state, user) {
      // console.log("여기는??");
      state.user = user;
    },
    SET_LOGIN_USER(state, user) {
      state.loginUser = user;
    },
    LOGOUT(state) {
      state.loginUser = null;
    },



    CREATE_BOARD(state, board){
      state.boards.push(board)
    },
    GET_BOARDS(state, boards){
      state.boards = boards;
    },
    GET_BOARD(state, board){
      state.board = board
    },
    GET_USER_BOARD(state, filteredBoards){
      state.userBoards = filteredBoards
    },
    UPDATE_BOARD(state, board){
      state.board = board
    },
    
    CREATE_BOARD_COMMENT(state, boardComment) {
      state.boardComments.push(boardComment);
    },
    GET_BOARD_COMMENTS(state, boardComments) {
      state.boardComments = boardComments;
    },
    UPDATE_BOARD_COMMENT(state, boardComment) {
      state.boardComment = boardComment;
    },

    // ADD_REPORT_BOARD(state, id) {
    //   if (state.reports[id]) {
    //     state.reports[id]++;
    //   } else {
    //     state.reports[id] = 1;
    //   }
    // },
    ADD_REPORT_BOARD(state, { boardId, userId }) {
      // 이미 신고한 게시물인지 확인
      // const isReported = state.reportedReports.some(
      //   (report) => report.id === id && report.userId === userId
      // );
      
      let isReported = false;
      for(let i = 0; i < state.reportedReports.length; i++) {
        if(state.reportedReports[i].boardId === boardId && state.reportedReports[i].userId === userId) {
          isReported = true;
        }
      }

      if (isReported) {
        alert('이미 신고한 게시글입니다.');
      } else {
        if (state.reports[boardId]) {
          state.reports[boardId]++;
        } else {
          state.reports[boardId] = 1;
        }
        // 신고된 게시물과 유저 정보 저장
        state.reportedReports.push({ boardId, userId });
        alert('게시물이 신고되었습니다.');
      }
    },
    RESET_REPORT_BOARD(state, boardId) {
      state.reports[boardId] = 0;
    },

    ADD_MY_VIDEO(state, myVideo){
      console.log(Array.isArray(state.myVideo));
      state.myVideos.push(myVideo);
    },

    GET_MY_VIDEOS(state, myVideos) {
      state.myVideos = myVideos;
    },

    // ADD_LIKE_VIDEO(state, {userSeq, videoId}) {
    //   state.likeVideos.push({userSeq, videoId});
    // },
    
    // GET_LIKE_VIDEOS(state, likevideos) {
    //   state.likeVideos = likevideos
    // }
    
  },
  actions: {
    searchExercise({commit}, payload){
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          q: payload,
          type: "video",
          maxResults: 10,
        },
      })
        .then((res) => {
          commit("SEARCH_EXERCISE", res.data.items)
        })
        .catch((err) => console.log(err));
    },
    //payload : 비디오 객체가 들어옴
    clickVideo({commit}, payload){
      commit("CLICK_VIDEO", payload)
    },
    
    getVideoComments({commit}, videoComments){
      let params = null;
      if(videoComments) params = videoComments;

      //controller에 get매핑 구현해놓기
      const API_URL = `${REST_API}/videoapi/videocomment`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
      .then((res)=>{
        // console.log(res.data);
        commit("GET_VIDEO_COMMENTS", res.data);
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    addMyVideo({commit}, myVideo) {
      const API_URL = `${REST_API}/userapi/like/${myVideo.videoId}`;
      console.log(myVideo.videoId);
      console.log(myVideo.id);
      axios({
        url: API_URL,
        method: "POST",
        //  data: myVideo,
        data: myVideo,
      })
      .then(()=>{
        commit("ADD_MY_VIDEO", myVideo);
        router.push(`/${myVideo.vdieoId}`);
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    getMyVideos({commit}, myVideos){
      let params = null;
      if(myVideos) params = myVideos;

      const API_URL = `${REST_API}/userapi/like/`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
      .then((res)=>{
        // console.log(res.data);
        commit("GET_MY_VIDEOS", res.data);
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    
    createVideoComment({commit}, videoComment) {
      const API_URL = `${REST_API}/videoapi/${videoComment.videoId}`;
      console.log(videoComment.nickname);
      console.log(videoComment.content);
      console.log(videoComment.videoId);
      console.log(videoComment.id);
      axios({
        url: API_URL,
        method: "POST",
        params: videoComment,
      })
      .then(()=>{
        console.log(videoComment.content);
        commit("CREATE_VIDEO_COMMENT", videoComment);
        alert("댓글이 등록되었습니다.");
        router.push(`/${videoComment.vdieoId}`);
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    updateVideoComment({ commit }, {videoId, videoComment}){
      const API_URL = `${REST_API}/videoapi/${videoId}/${videoComment.commentSeq}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: videoComment,
      }).then(()=> {
        commit("UPDATE_VIDEO_COMMENT", videoComment);
        alert("수정되었습니다.");
        if (router.currentRoute.path !== `/${videoComment.videoId}`) {
        router.push(`/${videoComment.videoId}`);
        }
      })
      .catch((err)=>{
        console.log(err)
      });
    },

    deleteVideoComment({ commit }, {videoId, videoCommentId}){
      const API_URL = `${REST_API}/videoapi/${videoId}/${videoCommentId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
      .then(()=> {
        commit
      })
      .catch((err)=> {
        console.log(err);
      })
    },
    


    
    createUser({ commit }, user) {
      console.log(user)
      const API_URL = `${REST_API}/userapi/user`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          commit("CREATE_USER", user);
          alert("회원가입 되었습니다.");
          router.push("/");
        })
        .catch((err) => {
          alert("아이디, 이메일, 닉네임을 확인해주세요...");
          console.log(err);
        });
    },
    setUsers({ commit }) {
      const API_URL = `${REST_API}/userapi/user`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log("user setting...");
          console.log(res.data);
          commit("SET_USERS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateUser({commit}, user) {
      console.log(commit);
      const API_URL = `${REST_API}/userapi/user`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then(() => {
           alert("정보 수정 완료!");
          // 로그인 유저 정보도 업데이트
          commit("SET_LOGIN_USER", user);

          // 수정 끝나면 메인페이지로
          router.push("/");
        })
        .catch((err) => {
          // alert("중복된 닉네임입니다.");
          console.log(err);
        });
    },
    deleteUser ({ state }, id) {
      const API_URL = `${REST_API}/userapi/user/${id}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("회원 탈퇴 완료");
          let index;
          for (let i = 0; i < state.users.length; i++) {
            if (state.users[i].id === id) {
              index = i;
            }
          }
          state.users.splice(index, 1);
          router.push("/");
          state.loginUser = null;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setUser({ commit }, id) {
      const API_URL = `${REST_API}/userapi/user/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          // console.log("들어왔니?");
          commit("SET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setLoginUser ({ commit }, user) {
      const API_URL = `${REST_API}/userapi/user/${user.id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          let resUser = res.data;
          if (resUser.id === user.id && resUser.password === user.password) {
            alert("로그인 성공!");
            commit("SET_LOGIN_USER", res.data);
            router.push("/");
          } else {
            alert("로그인 실패");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    


    deleteBoard({ commit }, id){
      const API_URL = `${REST_API}/boardapi/board/${id}`;

      axios({
        url: API_URL,
        method: "DELETE",
      })
      .then(()=> {
        commit
        router.push({name: "boardList"});
      })
      .catch((err)=> {
        console.log(err);
      })
    },
    updateBoard({ commit }, board){
      const API_URL = `${REST_API}/boardapi/board`;
      axios({
        url: API_URL,
        method: "PUT",
        params: board,
      }).then(()=> {
        commit("UPDATE_BOARD", board);
        router.push({name: "boardDetail", params: {id: board.id}});
      });
    },
    getBoard({commit}, id){
      const API_URL = `${REST_API}/boardapi/board/${id}`;
      axios({
        url: API_URL,
        method: "GET"
      })
      .then((res)=> {
        commit("GET_BOARD", res.data);
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    getBoards({commit}, payload){
      let params = null;
      if(payload) params = payload;

      const API_URL = `${REST_API}/boardapi/board`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
      .then((res)=>{
        console.log(res);
        commit("GET_BOARDS", res.data);
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    getUserBoards({commit, state}, writer) {
      const API_URL = `${REST_API}/userapi/user/board/${writer}`
      axios({
        url: API_URL,
        method: "GET",
      })
      .then(()=>{
        const filteredBoards = state.boards.filter(board => board.writer === writer);
        commit("GET_USER_BOARD", filteredBoards);
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    createBoard({commit}, board){
      const API_URL = `${REST_API}/boardapi/board`;
      axios({
        url: API_URL,
        method: "POST",
        params: board,
      })
      .then(()=>{
        commit("CREATE_BOARD", board);
        alert("게시글이 등록되었습니다.");
        router.push({name : 'boardList'});
        // router.push("list")
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    // addReportBoard({ commit, state }, id) {
    //   commit('ADD_REPORT_BOARD', id);
    addReportBoard({ commit, state }, { boardId, userId }) {
      commit('ADD_REPORT_BOARD', { boardId, userId });

      if (state.reports[boardId] >= state.reportCnt) {
        // 신고 누적이 임계값을 초과한 경우 게시물 삭제
        const API_URL = `${REST_API}/boardapi/board/${boardId}`;

        axios({
          url: API_URL,
          method: "DELETE",
        })
        .then(()=> {
          // 삭제하면서 그 게시물id의 신고횟수 초기화
          commit('RESET_REPORT_BOARD', boardId);
          router.push({name: "boardList"});
        })
        .catch((err)=> {
          console.log(err);
        })
      }
    },

    resetReport({ commit }, boardId) {
      commit('RESET_REPORT_BOARD', boardId);
    },




    getBoardComments({commit}, boardComments){
      let params = null;
      if(boardComments) params = boardComments;

      const API_URL = `${REST_API}/boardcommentapi/boardcomment`;
      axios({
        url: API_URL,
        method: "GET",
        params,
      })
      .then((res)=>{
        // console.log(res.data);
        commit("GET_BOARD_COMMENTS", res.data);
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    createBoardComment({commit}, boardComment) {
      const API_URL = `${REST_API}/boardcommentapi/board/${boardComment.boardId}`;
      // console.log("댓글 작성할 때 보드 아이디");
      // console.log(boardComment.boardId);
      let boardId = boardComment.boardId;
      axios({
        url: API_URL,
        method: "POST",
        params: boardComment,
      })
      .then(()=>{
        commit("CREATE_BOARD_COMMENT", boardComment);
        alert("댓글이 등록되었습니다.");
        // console.log("router할 때 보드 아이디");
        // console.log(boardId);
        router.push(`/board/${boardId}`);
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    deleteBoardComment({ commit }, {boardId, boardCommentId}){
      const API_URL = `${REST_API}/boardcommentapi/board/${boardId}/${boardCommentId}`;
      console.log("index에서 받은 boardId와 boardCommentId");
      console.log(boardId);
      console.log(boardCommentId);
      axios({
        url: API_URL,
        method: "DELETE",
      })
      .then(()=> {
        commit
      })
      .catch((err)=> {
        console.log(err);
      })
    },

    updateBoardComment({ commit }, {boardId, boardComment}){
      // console.log("보드 아이디");
      // console.log(boardId);
      // console.log("댓글 아이디");
      // console.log(boardComment.id);
      // console.log("댓글 내용");
      // console.log(boardComment.content);
      const API_URL = `${REST_API}/boardcommentapi/board/${boardId}/${boardComment.id}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: boardComment,
      }).then(()=> {
        commit("UPDATE_BOARD_COMMENT", boardComment);
        alert("댓글이 수정되었습니다.");
        // router.push({name: "boardDetail", params: {boardId}});
      });
    },



    getLikeVideos({commit}, likeVideos) {
      let params = null;
      if(likeVideos) params = likeVideos;
      
      const API_URL = `${REST_API}/userapi/like`

      axios({
        url: API_URL,
        method: "GET",
        params,
      })
      .then((res) => {
        commit("GET_LIKE_VIDEOS", res.data);
      })
    }

  },
  modules: {
  }
})
