<template>
  <div>
    <WriteHeader @saveData="saveData()" @saveCustomData="saveCustomData()" />
    <form id="formData" action="">
      <AttachPhoto
        :imgUrlArray="imgUrlArray"
        :imgUrlArrayLength="imgUrlArrayLength"
        :isAttached="isAttached"
        @getImageUrl="getImageUrl($event)"
        @deletePhoto="deletePhoto($event)"
      />
      <InputGroup
        @getTitle="getTitle($event)"
        @getPrice="getPrice($event)"
        @getContent="getContent($event)"
        :title="title"
        :price="price"
        :content="content"
      />
    </form>
  </div>
</template>

<script>
import WriteHeader from "../components/WriteHeader.vue";
import AttachPhoto from "../components/AttachPhoto.vue";
import InputGroup from "../components/InputGroup.vue";
import { mapMutations, mapState } from "vuex";
import axios from 'axios';

export default {
  name: "ProductWrite",
  data() {
    return {
      id: 0,
      title: "",
      price: null,
      content: "",
      imgUrlArray: [],
      imgUrlArrayLength: 0,
      isAttached: false,
      customIdx: null,
      definedData: null,
    };
  },
  components: {
    WriteHeader,
    AttachPhoto,
    InputGroup,
  },
  computed: {
    ...mapState(["userInfo", "productListData"]),
  },
  methods: {
    ...mapMutations(["toggleHeaderMenu", "updateProductList"]),

    getTitle(value) {
      this.title = value;
    },

    getPrice(value) {
      this.price = value;
    },

    getContent(value) {
      this.content = value;
    },

    // 사진 첨부
    getImageUrl(e) {
      const files = e.files;
      if (files.length <= 10 && this.imgUrlArray.length + files.length <= 10) {
        for (const [key, value] of Object.entries(files)) {
          const url = URL.createObjectURL(value);
          this.imgUrlArray.push(url);
          this.imgUrlArrayLength = this.imgUrlArray.length;
        }
      } else {
        alert("사진은 최대 10장까지 첨부할 수 있습니다.");
      }

      if (this.imgUrlArray != null) {
        this.isAttached = true;
      }
    },

    // 사진 삭제
    deletePhoto(idx) {
      this.imgUrlArray.splice(idx, 1);
      this.imgUrlArrayLength = this.imgUrlArray.length;

      if (this.imgUrlArray == 0) {
        this.isAttached = false;
        this.imgUrlArray = [];
      }
    },

    // define post Data
    defineData() {
      const datetime = new Date();
      this.definedData = {
        id: this.id,
        title: this.title,
        price: this.price,
        content: this.content,
        category: "전자기기",
        productMainImage: this.imgUrlArray[0],
        productImages: this.imgUrlArray,
        userId: this.userInfo.id,
        userNickName: this.userInfo.nickName,
        userLocation: this.userInfo.location,
        userImage: this.userInfo.image,
        chatCnt: 0,
        likeCnt: 0,
        viewCnt: 0,
        datetime: datetime.toLocaleTimeString(),
      };
    },

    // 작성된 데이터 저장 (완료버튼 클릭시)
    saveData() {
      let dataArr = [];
      let oldArr = this.productListData;

      // localStorage상에 productListData 유무 체크
      if (oldArr === null) {
        oldArr = [];
        console.log("oldArr1", oldArr);
      } else {
        dataArr = oldArr;
      }

      // 필수값 처리
      if (this.imgUrlArrayLength == 0) {
        alert("사진을 첨부해주세요.");
      } else if (this.title == "") {
        alert("제목을 입력해주세요.");
      } else if (this.price == "") {
        alert("가격을 입력해주세요.");
      } else if (this.content == "") {
        alert("내용을 입력해주세요.");
      } else {
        // 새롭게 생성된 게시글 데이터 정의
        this.defineData();

        // 기존 배열에 추가
        dataArr.unshift(this.definedData);
        
        var frm = new FormData();
        var photoFile = document.getElementById("buttonAttach");
        frm=document.getElementById("formData");
        // localStorage에 저장
        window.localStorage.setItem("productListData", JSON.stringify(dataArr));
       
		axios.post('/api/board/write', frm).then(function (res) {
       var frm = new FormData();
       console.log(res);
       frm.append("board_seq",res.data.board_seq);
       frm.append("buttonAttach", photoFile.files[0]);
			 axios.post('/api/board/upload/files', frm, {headers:{
	        'Content-Type':'multipart/form-data'}}).then(function (response) {
			});
		});
        this.updateProductList();
        this.$router.push("/");
      }
    },

    // 수정하기 데이터 불러오기
    getCustomData() {
      let customList = this.productListData;

      const target = customList.find((item) => {
        if (item.id == this.id) {
          return true;
        }
      });
      this.customIdx = customList.indexOf(target);
      console.log("this.customIdx", this.customIdx);

      this.title = customList[this.customIdx].title;
      this.price = customList[this.customIdx].price;
      this.content = customList[this.customIdx].content;
      this.imgUrlArray = customList[this.customIdx].productImages;
      this.imgUrlArrayLength = customList[this.customIdx].productImages;
    },

    // 수정하기
    saveCustomData() {
      let dataArr = [];
      let oldArr = this.productListData;

      // localStorage상에 productListData 유무 체크
      if (oldArr === null) {
        oldArr = [];
      } else {
        dataArr = oldArr;
      }

      // 필수값 처리
      if (this.imgUrlArrayLength == 0) {
        alert("사진을 첨부해주세요.");
      } else if (this.title == "") {
        alert("제목을 입력해주세요.");
      } else if (this.price == "") {
        alert("가격을 입력해주세요.");
      } else if (this.content == "") {
        alert("내용을 입력해주세요.");
      } else {
        // 수정 데이터 정의
        this.defineData();
        dataArr.splice(this.customIdx, 1, this.definedData);

        // localStorage에 저장
        window.localStorage.setItem("productListData", JSON.stringify(dataArr));

        // url변경 및 수정하기 팝업 제거
        this.$router.push(`/view/${this.id}`);
        this.toggleHeaderMenu();
      }
    },
  },

  mounted() {
    if (this.$route.params.id) {
      // 수정할 데이터 불러오기
      this.id = Number(this.$route.params.id);
      this.getCustomData();
    } else {
      // 데이터의 id붙이기
      if (this.productListData === null) {
        this.id = 0;
      } else {
        const lastId = this.productListData[0].id;
        this.id = lastId + 1;
      }
    }
  },
};
</script>
