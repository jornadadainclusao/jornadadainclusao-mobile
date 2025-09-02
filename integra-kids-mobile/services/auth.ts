// autenticador provisório

import { api } from "./api";

export async function login(email: string, senha: string) {
  const res = await api.post("/auth/login", { email, senha });
  return res.data; // deve retornar { user, token }
}

export async function logout() {
  return api.post("/auth/logout");
}

export async function getProfile(token: string) {
  const res = await api.get("/users/me", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}
