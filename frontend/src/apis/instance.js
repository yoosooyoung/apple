import axios from "axios";

export const instance = axios.create({
  baseURL: "/api/board/",
  headers: { "Content-Type": "application/json" },
});
