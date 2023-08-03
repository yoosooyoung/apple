import { createStore } from "vuex";
import router from "./router";
import axios from "axios";

const store = createStore({
  state() {
    return {
      productListData: null,
      searchProductListData: JSON.parse(
        window.localStorage.getItem("productListData")
      ),
      userInfo: JSON.parse(window.sessionStorage.getItem("user_info")),
      HeaderMenuIsShow: false,
      searchMode: false,
      searchResultMode: false,
      searchKeyword: "",
      recentKeyword: JSON.parse(window.localStorage.getItem("recentKeyword")),

      // 로그인
      loginId: "",
      loginPw: "",

      // 회원가입
      joinId: "",
      joinPassword: "",
      joinPassword2: "",
      joinName: "",
      joinEmail: "",
      joinAddress: "",
      joinAddress2: "",
      joinProfile: "",
      joinProfileName: "",

      idCheck: "", // '' / null / rule / overlap / pass
      pwCheck: "", // '' / null / rule/ pass
      pwCheck2: "", // '' / null / rule/ pass
      nameCheck: "", // '' / null / rule/ pass
      emailCheck: "", // '' / null / rule/ pass
      addressCheck: "", // '' / null /  pass
      addressCheck2: "", // '' / null /  pass
    };
  },
  updated(store) {
    store.dispatch("dispatch");
  },
  computed: {},
  mutations: {
    // state 변경은 mutation에서

    /* 상품리스트 업데이트 */
    updateProductList(state, response) {
      state.productListData = response.data.list;
      console.log("updateProductList");
      console.log(state.productListData);
    },

    /* id 중복체크 */
    idCheckOverlap(state) {
      const frm = new FormData();
      frm.append("user_id", state.joinId);

      if (state.idCheck !== "null" && state.idCheck !== "rule") {
        axios
          .post("/api/join/idCheck", frm)
          .then(function (response) {
            if (response.data.result === "0") {
              state.idCheck = "pass";
            } else if (response.data.result === "1") {
              state.idCheck = "overlap";
            }
          })
          .catch(function (err) {
            alert("오류발생. 관리자에게 문의해주세요");
            console.log(err);
          });
      }
    },

    /* 회원가입 */
    getJoinId(state, value) {
      state.joinId = value;

      // id 유효성 체크
      const regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,12}$/;

      if (state.joinId === "") {
        state.idCheck = "null";
      } else if (!regExp.test(state.joinId)) {
        state.idCheck = "rule";
      } else {
        state.idCheck = "";
      }
    },
    getJoinPassword(state, value) {
      state.joinPassword = value;

      // pw 유효성 체크
      const regExp =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,20}$/;

      if (state.joinPassword === "") {
        state.pwCheck = "null";
      } else if (!regExp.test(state.joinPassword)) {
        state.pwCheck = "rule";
      } else {
        state.pwCheck = "pass";
      }
    },
    getJoinPassword2(state, value) {
      state.joinPassword2 = value;

      if (state.joinPassword2 === "") {
        state.pwCheck2 = "null";
      } else if (state.joinPassword !== state.joinPassword2) {
        state.pwCheck2 = "rule";
      } else {
        state.pwCheck2 = "pass";
      }
    },
    getJoinName(state, value) {
      state.joinName = value;

      // name 유효성 체크
      const regExp = /^[ㄱ-ㅎ가-힣-A-Za-z0-9]{2,12}$/;

      if (state.joinName === "") {
        state.nameCheck = "null";
      } else if (!regExp.test(state.joinName)) {
        state.nameCheck = "rule";
      } else {
        state.nameCheck = "pass";
      }
    },
    getJoinEmail(state, value) {
      state.joinEmail = value;

      // email 유효성 체크
      const regExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

      if (state.joinEmail === "") {
        state.emailCheck = "null";
      } else if (!regExp.test(state.joinEmail)) {
        state.emailCheck = "rule";
      } else {
        state.emailCheck = "pass";
      }
    },

    getJoinProfile(state, value) {
      if (value.files[0] == undefined) {
        state.joinProfile = "";
        state.joinProfileName = "";
      } else {
        state.joinProfile = value.files[0];
        state.joinProfileName = value.files[0].name;
      }
    },

    clearJoinValidation(state) {
      state.idCheck = "";
      state.pwCheck = "";
      state.pwCheck2 = "";
      state.nameCheck = "";
      state.emailCheck = "";
      state.addressCheck = "";
      state.addressCheck2 = "";
    },

    /* 로그인 */
    getLoginId(state, value) {
      state.loginId = value;
    },
    getLoginPw(state, value) {
      state.loginPW = value;
    },
    login(state) {
      if (state.loginId == "") {
        alert("아이디를 입력해주세요.");
      } else if (state.loginPW == "") {
        alert("비밀번호를 입력해주세요.");
      } else {
        var loginForm = new FormData();
        loginForm.append("user_id", state.loginId);
        loginForm.append("user_pw", state.loginPW);
        navigator.geolocation.getCurrentPosition(function (position) {
          loginForm.append("latitude", position.coords.latitude);
          loginForm.append("longitude", position.coords.longitude);
          console.log(loginForm);
          axios.post("/api/signin/check", loginForm).then(function (response) {
            if (response.data.result == "success") {
              state.userInfo = {
                // FRONT TO DO : 데이터 추가 완료시, USER 데이터 업데이트해주기
                id: response.data.member.user_id,
                nickName: response.data.member.nick_name,
                image: response.data.member.user_image,
                location: response.data.address,
              };
              sessionStorage.setItem(
                "user_info",
                JSON.stringify(state.userInfo)
              );
            } else if (response.data.result == 0 || response.data.result == 1) {
              alert("로그인정보가 일치하지 않습니다. 다시한번 확인해주세요.");
            }
          });
        });
      }
    },

    /* 로그아웃 */
    logout(state) {
      sessionStorage.removeItem("user_info");
      state.userInfo = null;
      router.push("/");
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
        window.sessionStorage.setItem(
          "userInfo",
          JSON.stringify(state.userInfo)
        );
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

    /* 상세 - 글  */
    deletePost(state, id) {
      let wishIds = state.userInfo.liked;

      // axios.post 두번째 인자에 null을 넣으면 데이터가 제대로 넘어감 (임시방편 느낌이라 추후에 변경 필요)
      axios
        .post("/api/board/delete", null, { params: { board_seq: id } })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });

      /*
      if (state.productListData.length > 0) {
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
      */

      window.location.href = "/";
      //router.push("/"); 새로 고침이 안됨
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

  actions: {
    //ajax는 actions에서
    getProductList(context) {
      axios.get("/api/board/list").then(function (response) {
        context.commit("updateProductList", response);
      });
    },

    /* 회원가입 */
    join(context) {
      // 유효성 검사(필수값 체크)
      if (
        context.state.idCheck === "pass" &&
        context.state.pwCheck === "pass" &&
        context.state.pwCheck2 === "pass" &&
        context.state.nameCheck === "pass" &&
        context.state.emailCheck === "pass"
      ) {
        var joinForm = document.getElementById("joinForm");
        axios
          .post("/api/join", joinForm)
          .then(function (response) {
            if (response.data.result === "success") {
              alert("회원가입이 완료되었습니다");
              router.push("/");
            } else if (response.data.result == 1) {
              alert("중복된 ID입니다.");
            }
          })
          .catch(function (err) {
            alert("오류발생. 관리자에게 문의해주세요");
            console.log(err);
          });
        context.commit("clearJoinValidation");
      } else {
        alert("필수값을 채워주세요.");
      }
    },
  },
});

export default store;
