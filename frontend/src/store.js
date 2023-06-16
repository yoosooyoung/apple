import { createStore } from "vuex";
import router from "./router";
import axios from "axios";

const store = createStore({
  state() {
    return {
      productListData: JSON.parse(
        window.localStorage.getItem("productListData")
      ),
      searchProductListData: JSON.parse(
        window.localStorage.getItem("productListData")
      ),
      userName: "",
      userId: "",
      userPassword: "",
      userInfo: JSON.parse(window.localStorage.getItem("userInfo")),
      HeaderMenuIsShow: false,
      searchMode: false,
      searchResultMode: false,
      searchKeyword: "",
      recentKeyword: JSON.parse(window.localStorage.getItem("recentKeyword")),
    };
  },
  mutations: {
    /* 상품리스트 업데이트 */
    updateProductList(state) {
      state.productListData = JSON.parse(
        window.localStorage.getItem("productListData")
      );
    },

    /* 회원가입 */
    getUserId(state, value) {
      state.userId = value;
    },
    getUserName(state, value) {
      state.userName = value;
    },
    getUserPassword(state, value) {
      state.userPassword = value;
    },
    join(state) {
      if (state.userId == "") {
        alert("아이디를 입력해주세요.");
      } else if (state.userName == "") {
        alert("닉네임을 입력해주세요.");
      } else if (state.userPassword == "") {
        alert("비밀번호를 입력해주세요.");
      } else {
        (state.userInfo = {
          userId: state.userId,
          nickName: state.userName,
          password: state.userPassword,
          location: "노원구 공릉동",
          locationDong: "공릉동",
          liked: [],
          image: null,
        }),
          console.log(state.userInfo);
        const frm = new FormData();
        frm.append("user_id", state.userInfo.userId);
        frm.append("user_pw", state.userInfo.password);
        axios.post("/api/signin/check", frm).then(function (response) {
          console.log(response);
        });
      }
    },

    /* 마이페이지 - 정보수정 */
    getUserImage(state, target) {
      if (target != undefined) {
        const file = target.files[0];
        const url = URL.createObjectURL(file);
        state.userInfo.image = url;
      }
    },
    getUserNickName(state, value) {
      state.userInfo.nickName = value;
    },
    saveMyInfo(state) {
      if (state.userInfo.nickName == "") {
        alert("닉네임을 입력해주세요");
      } else {
        window.localStorage.setItem("userInfo", JSON.stringify(state.userInfo));
        alert("정보수정이 완료되었습니다.");
        router.push("/mypage");
      }
    },

    /* 상세  - 좋아요 */
    toggleWish(state, id) {
      let likedList = state.userInfo.liked;

      // userInfo 좋아요 리스트에서 삭제
      const targetInUserInfo = likedList.filter((item) => {
        item == id;
      });
      const idxInUserInfo = likedList.indexOf(targetInUserInfo);

      // productListData 좋아요 카운팅 조절
      let targetInPrd = state.productListData.filter((item) => id == item.id);
      const idxInPrd = state.productListData.indexOf(targetInPrd[0]);

      if (likedList.includes(id)) {
        state.userInfo.liked.splice(idxInUserInfo, 1);
        state.productListData[idxInPrd].likeCnt--;
      } else {
        state.userInfo.liked.push(id);
        state.productListData[idxInPrd].likeCnt++;
      }
      window.localStorage.setItem("userInfo", JSON.stringify(state.userInfo));
      window.localStorage.setItem(
        "productListData",
        JSON.stringify(state.productListData)
      );
    },

    /* 상세 - 글 수정페이지로 이동 */
    editPost(state, id) {
      router.push(`/write/${id}`);
    },

    /* 상세 - 글 삭제 */
    deletePost(state, id) {
      let wishIds = state.userInfo.liked;

      if (state.productListData.length == 1) {
        state.productListData = null;
        window.localStorage.removeItem("productListData");
      } else {
        const newDataList = state.productListData.filter(
          (item) => item.id != id
        );
        state.productListData = newDataList;
        window.localStorage.setItem(
          "productListData",
          JSON.stringify(state.productListData)
        );

        // 글 삭제시, 위시리스트에 있던 데이터도 삭제
        const newWish = wishIds.filter((item) => item !== id);

        if (newWish !== wishIds) {
          state.userInfo.liked = newWish;
          // userInfo 좋아요 리스트에서 삭제
          window.localStorage.setItem(
            "userInfo",
            JSON.stringify(state.userInfo)
          );
        }
      }
      router.go(-1);
      state.HeaderMenuIsShow = false;
    },

    /* 상세 - 메뉴 더보기 */
    toggleHeaderMenu(state) {
      if (state.HeaderMenuIsShow == false) {
        state.HeaderMenuIsShow = true;
      } else {
        state.HeaderMenuIsShow = false;
      }
    },

    /* 상세  - 공유하기 */
    copyUrl() {
      const urlArea = document.createElement("textarea");

      document.body.appendChild(urlArea);
      urlArea.value = window.document.location.href;
      urlArea.select(); //urlArea를 설정
      document.execCommand("copy"); // 복사
      document.body.removeChild(urlArea);

      alert("URL이 복사되었습니다."); // 알림창
    },

    /* 검색기능 */
    searchItem(state, value) {
      if (value !== "") {
        // 검색결과 필터링
        state.searchKeyword = value;
        state.searchResultMode = true;

        if (state.productListData !== null) {
          state.searchProductListData = state.productListData.filter(
            (item) => item.title.includes(value) || item.content.includes(value)
          );
          if (state.searchProductListData.length == 0) {
            state.searchProductListData = null;
          }
        } else {
          state.searchProductListData = null;
        }

        // 최근검색어에 등록
        if (state.recentKeyword == null) {
          state.recentKeyword = [];
        }
        state.recentKeyword.unshift(value);
        window.localStorage.setItem(
          "recentKeyword",
          JSON.stringify(state.recentKeyword)
        );
      } else {
        alert("검색어를 입력해주세요.");
      }
    },
    // 수정중 감지
    searchEditing(state, value) {
      state.searchKeyword = value;
      state.searchResultMode = false;
    },
    //button delete 클릭시 인풋 클리어
    clearInput(state) {
      state.searchKeyword = "";
      state.searchResultMode = false;
    },
    // 최근검색어 키워드 지우기
    deleteRecentKeyowrd(state, idx) {
      if (state.recentKeyword.length == 1) {
        state.recentKeyword = null;
      } else {
        state.recentKeyword.splice(idx, 1);
      }
      window.localStorage.setItem(
        "recentKeyword",
        JSON.stringify(state.recentKeyword)
      );
    },
    // 최근검색어 키워드 all
    deleteRecentKeyowrdAll(state) {
      state.recentKeyword = null;
      window.localStorage.setItem(
        "recentKeyword",
        JSON.stringify(state.recentKeyword)
      );
    },
  },
});

export default store;
