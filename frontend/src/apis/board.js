import { instance } from "./instance";

// 상세 가져오기
export const callBoardView = async (id) => {
  try {
    const res = await instance.get(`view/${id}`);
    return res;
  } catch (err) {
    console.error();
    throw err;
  }
};
