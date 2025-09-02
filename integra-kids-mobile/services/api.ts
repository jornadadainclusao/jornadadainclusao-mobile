// adição de API provisória

import axios from "axios";

// 👉 Use o IP da sua máquina ou o domínio do servidor
// Se for rodar no emulador Android: use "http://10.0.2.2:3000"
// Se for no Expo Go no celular real: use o IP da máquina na rede local
export const api = axios.create({
  baseURL: "http://192.168.0.100:3000/api", // troque para seu backend
  timeout: 10000,
});
