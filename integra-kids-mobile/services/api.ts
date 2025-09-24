// services/api.ts
import axios, { AxiosInstance, AxiosResponse } from "axios";
import MMKVStorage from "react-native-mmkv-storage";
import * as FileSystem from "expo-file-system";
// import * as Sharing from "expo-sharing";

// ---------------------------
// Tipos
// ---------------------------
export interface Usuario {
  id: number;
  nome: string;
}

export interface Dependente {
  id?: number;
  nome: string;
  dataNascimento?: string;
  idade?: number;
  sexo: string;
  foto?: string;
  usuario_id_fk?: number;
}

export interface UserData {
  usuario: Usuario;
  token: string;
}

export type SetDados<T> = (data: T) => void;

const MMKV = new MMKVStorage.Loader().initialize();
// ---------------------------
// Instância Axios
// ---------------------------
export const api: AxiosInstance = axios.create({
  baseURL: "https://backend-9qjw.onrender.com/",
});

// Adiciona token automaticamente
api.interceptors.request.use(async (config) => {
  const token = await MMKV.get("token");
  if (token && config.headers) {
    config.headers.Authorization = token;
  }
  return config;
});

// Interceptor global de erros
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error("Erro na requisição:", error.response?.data || error.message);
    return Promise.reject(error);
  }
);

// ---------------------------
// Usuário
// ---------------------------
export const cadastrarUsuario = async <T>(url: string, dados: object, setDados: SetDados<T>) => {
  const resposta = await api.post<T>(url, dados);
  setDados(resposta.data);
};

export const login = async <T>(url: string, dados: object, setDados: SetDados<T>) => {
  const resposta = await api.post<T>(url, dados);
  setDados(resposta.data);
};

export const atualizarUsuario = async (dadosAtualizados: object) => {
  const response = await api.patch("/usuarios/atualizar-parcial", dadosAtualizados);
  return response.data;
};

export const deletarUsuario = async (id: number) => {
  const response = await api.delete(`/usuarios/${id}`);
  return response.data;
};

// ---------------------------
// Dependentes
// ---------------------------
export const insereDependenteBd = async (dependente: Dependente) => {
  const response = await api.post("/dependente", dependente);
  return response.data;
};

export const fetchDependentes = async (): Promise<Dependente[]> => {
  const usuarioJson = await MMKV.get("usuario");
  const token = await MMKV.get("token");
  if (!usuarioJson || !token) return [];

  const usuario: Usuario = JSON.parse(usuarioJson);
  const response = await api.get<Dependente[]>(`/dependente/getDependenteByIdUsuario/${usuario.id}`);
  return response.data;
};

export const updateDependente = async (id: number, formData: Partial<Dependente>, avatarSelecionado?: string) => {
  const usuarioJson = await MMKV.get("usuario");
  if (!usuarioJson) throw new Error("Usuário não encontrado");

  const usuario: Usuario = JSON.parse(usuarioJson);
  const body = { ...formData, foto: avatarSelecionado, usuario_id_fk: usuario.id };
  const response = await api.patch(`/dependente/${id}`, body);
  return response.data;
};

export const deleteDependente = async (id: number) => {
  const response = await api.delete(`/dependente/${id}`);
  return response.data;
};

// ---------------------------
// Player
// ---------------------------
export const escolherDependenteComoPlayer = async (dependente: Dependente) => {
  if (!dependente.id || !dependente.nome || !dependente.foto) return;
  await MMKV.set("player", JSON.stringify(dependente));
};

// ---------------------------
// Jogos
// ---------------------------
export const registrarJogo = async <T>(url: string, dados: object, setDados: SetDados<T>, header?: object) => {
  const resposta: AxiosResponse<T> = await api.post(url, dados, header);
  setDados(resposta.data);
};

// ---------------------------
// Email / Tokens
// ---------------------------
export const Posttoken = async (url: string): Promise<AxiosResponse<any>> => api.post(url);
export const Gettoken = async (url: string): Promise<AxiosResponse<any>> => api.get(url);

// ---------------------------
// Downloads PDF / Excel
// ---------------------------
// const downloadFile = async (url: string, filename: string) => {
//   const token = await MMKV.get("token") || "";
//   const fileUri = FileSystem.documentDirectory + filename;

//   const downloadResumable = FileSystem.createDownloadResumable(url, fileUri, {
//     headers: { Authorization: token },
//   });

//   try {
//     const { uri } = await downloadResumable.downloadAsync();
//     if (await Sharing.isAvailableAsync()) {
//       await Sharing.shareAsync(uri);
//     }
//   } catch (error) {
//     console.error("Erro ao baixar arquivo:", error);
//   }
// };

export const downloadPdfInfoJogos = async (dependenteId: number) =>
  downloadFile(`/dependente/exportPdf/${dependenteId}`, "relatorio.pdf");

export const downloadExcelInfoJogos = async (dependenteId: number) =>
  downloadFile(`/dependente/exportExcel/${dependenteId}`, "relatorio.xlsx");
