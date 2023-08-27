import axios from "axios";

export const instance = axios.create({
  baseURL: "/api/",
  headers: { "Content-Type": "application/json" },
});
