import Vue from "vue";
import VueRouter from "vue-router";

import BoardView from "../views/BoardView.vue";
import SsafitView from "../views/SsafitView.vue";
import SearchView from "../views/SearchView.vue";
import VideoView from "../views/VideoView.vue";

import LoginForm from '@/components/LoginForm.vue';
import User from '../views/User.vue';
import UserRegist from "@/components/user/UserRegist.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserBoard from "@/components/user/UserBoard.vue";
import UserBoardList from "@/components/user/UserBoardList.vue";
import UserLikeList from "@/components/user/UserLikeList.vue";

import BoardCreate from "../components/board/BoardCreate.vue";
import BoardList from "../components/board/BoardList.vue";
import BoardDetail from "../components/board/BoardDetail.vue";
import BoardUpdate from "../components/board/BoardUpdate.vue";

Vue.use(VueRouter);

// meta를 이용해서 각 컴포넌트마다 탭의 title 설정
const routes = [
    {
    path: "/",
    name: "ssafit",
    component: SsafitView,
    meta: {
      title: "SSAFIT"
    }
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    meta: {
      title: "SSAFIT Board"
    },
    children: [
      {
        path: "list",
        name: "boardList",
        component: BoardList,
        meta: {
          title: "게시글 목록"
        },
      },
      {
        path: "create",
        name: "boardCreate",
        component: BoardCreate,
        meta: {
          title: "게시글 작성"
        },
      },
      {
        path: ":id",
        name: "boardDetail",
        component: BoardDetail,
        meta: {
          title: "게시글 상세"
        },
      },
      {
        path: "update",
        name: "boardUpdate",
        component: BoardUpdate,
        meta: {
          title: "게시글 수정"
        },
      },
    ],
  },
  {
    path: "/search",
    name: "search",
    component: SearchView,
    meta: {
      title: "영상 검색결과"
    },
  },
  {
      path: "/:videoId",
      name: "video",
      component: VideoView,
      meta: {
        title: "영상 상세"
      },
  },
  {
    path: '/login',
    name: 'login',
    component: LoginForm,
    meta: {
      title: "로그인"
    },
  },
  {
    path: "/user",
    component: User,
    children: [
      {
        path: "/regist",
        name: "userRegist",
        component: UserRegist,
        meta: {
          title: "회원가입"
        },
      },
      {
        path: "/:id/detail",
        name: "userDetail",
        component: UserDetail,
        meta: {
          title: "마이페이지"
        },
      },
      {
        path: "/:id",
        name: "userBoard",
        component: UserBoard,
        meta: {
          title: "마이페이지"
        },
      },
      {
        path: "/:id/:writer",
        name: "userBoardList",
        component: UserBoardList,
        meta: {
          title: "작성한 게시글"
        },
      },
      {
        path: "/:id/video",
        name: "userLikeList",
        component: UserLikeList,
        meta: {
          title: "영상 찜목록"
        },
      },
    ]
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// beforEach(라우트 이동 전에 호출되는 콜백 함수)를 통해 타이틀을 동적으로 변경
// to: 이동하려는 라우트의 라우트 객체, from: 현재 위치한 라우트의 라우트 객체, next: 이동을 제어하는 함수로 호출해야 다음 단계로 진행
router.beforeEach((to, from, next) => {
  const defaultTitle = 'SSAFIT'; // 기본 타이틀
  const pageTitle = to.meta.title || defaultTitle; // 현재 라우트의 메타 데이터에서 타이틀 가져오기
  document.title = pageTitle; // 문서의 타이틀 변경
  next();
});

export default router;