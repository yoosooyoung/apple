import { instance, formInstance } from "./instance";

export const getOneBoardRequest = async (id) => {
  try {
    const result = await instance.get(`board/view/${id}`);
    return result;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// 작성하기
export const createBoardRequest = async (data) => {
  try {
    const result = await instance.post(`board/write`, data);
    return result;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// 작성하기 - 사진 첨부
export const createBoardImageRequest = async (data) => {
  try {
    const result = await formInstance.post(`board/upload/files`, data);
    return result;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
