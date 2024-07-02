import axios from 'axios';

const api = axios.create({
  baseURL:'http://localhost:8080',
  timeout:10000,
  headers:{
    'Content-type':'application/json',
  }
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if(token !== null){
    config.headers.Authorization = "Bearer " + token
  }
  return config;
})

export default api;