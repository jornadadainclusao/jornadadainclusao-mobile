// ideia provisória de adicionar o backend, sujeito a mudanças

import { createContext, useState, ReactNode, useEffect } from "react";
import * as SecureStore from "expo-secure-store"; // para guardar token com segurança
import { login, logout, getProfile } from "../services/auth";

type AuthContextType = {
  user: any;
  token: string | null;
  signIn: (email: string, senha: string) => Promise<void>;
  signOut: () => Promise<void>;
};

export const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<any>(null);
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    // tenta restaurar sessão ao abrir o app
    SecureStore.getItemAsync("token").then(async (savedToken) => {
      if (savedToken) {
        setToken(savedToken);
        const profile = await getProfile(savedToken);
        setUser(profile);
      }
    });
  }, []);

  async function signIn(email: string, senha: string) {
    const data = await login(email, senha);
    setToken(data.token);
    setUser(data.user);
    await SecureStore.setItemAsync("token", data.token);
  }

  async function signOut() {
    await logout();
    setToken(null);
    setUser(null);
    await SecureStore.deleteItemAsync("token");
  }

  return (
    <AuthContext.Provider value={{ user, token, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  );
}
