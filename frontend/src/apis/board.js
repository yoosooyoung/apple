import { instance } from "./instance";

export const getOneBoardRequest = async (id) => {
  try {
    const result = await instance.get(`board/view/${id}`);
    return result;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
