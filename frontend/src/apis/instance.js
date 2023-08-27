import axios from "axios";

export const instance = axios.create({
  baseURL: "/api/",
  headers: { "Content-Type": "application/json" },
});

export const formInstance = axios.create({
  baseURL: "/api/",
  headers: { "Content-Type": "multipart/form-data" },
});
