// context/AuthContext.tsx
import React, { createContext, useState, useEffect, ReactNode } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { login } from "../services/api"; // seu service unificado

// ---------------------------
// Tipos
// ---------------------------
export interface Usuario {
  id: number;
  nome: string;
  email?: string;
  usuario?: string;
  foto?: string;
  senha?: string;
  token?: string;
}

export interface AuthContextType {
  usuario: Usuario;
  handleLogin: (usuarioLogin: { usuario: string; senha: string }) => Promise<void>;
  handleLogout: () => Promise<void>;
  isLoading: boolean;
}

interface AuthProviderProps {
  children: ReactNode;
}

// ---------------------------
// Contexto
// ---------------------------
export const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [usuario, setUsuario] = useState<Usuario>({
    id: 0,
    nome: "",
    email: "",
    usuario: "",
    foto: "",
    senha: "",
    token: "",
  });

  const [isLoading, setIsLoading] = useState(false);

  // Recupera usuário e token do AsyncStorage ao iniciar
  useEffect(() => {
    const carregarUsuario = async () => {
      const token = await AsyncStorage.getItem("token");
      const dadosUsuario = await AsyncStorage.getItem("usuario");

      if (token && dadosUsuario) {
        const usuarioParse: Usuario = JSON.parse(dadosUsuario);
        setUsuario({ ...usuarioParse, token });
      }
    };

    carregarUsuario();
  }, []);

  // ---------------------------
  // Login
  // ---------------------------
  const handleLogin = async (usuarioLogin: { usuario: string; senha: string }) => {
    setIsLoading(true);
    try {
      await login("/usuarios/logar", usuarioLogin, async (resposta: Usuario) => {
        setUsuario(resposta);

        // Limpa storage antigo
        await AsyncStorage.clear();

        await AsyncStorage.setItem("token", resposta.token || "");
        await AsyncStorage.setItem(
          "usuario",
          JSON.stringify({
            id: resposta.id,
            nome: resposta.nome,
            email: resposta.usuario,
            foto: resposta.foto,
          })
        );
      });
    } catch (error) {
      console.error(error);
      throw new Error("Usuário ou senha inválidos");
    } finally {
      setIsLoading(false);
    }
  };

  // ---------------------------
  // Logout
  // ---------------------------
  const handleLogout = async () => {
    setUsuario({
      id: 0,
      nome: "",
      email: "",
      usuario: "",
      foto: "",
      senha: "",
      token: "",
    });

    await AsyncStorage.removeItem("token");
    await AsyncStorage.removeItem("usuario");
  };

  return (
    <AuthContext.Provider value={{ usuario, handleLogin, handleLogout, isLoading }}>
      {children}
    </AuthContext.Provider>
  );
};
