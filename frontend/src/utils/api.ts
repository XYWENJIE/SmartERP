import axios from 'axios';
import {toast} from 'sonner';

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

api.interceptors.response.use((response) => {
  return response;
},(error) => {
  console.log(error)
  if(error.response.status === 401){
    localStorage.removeItem("token");
    window.location.href = '/login'
  }
  if(error.response.status === 500){
    toast.error(error.response.data.message,{duration:20000});

  }
  return Promise.reject(error);
})

export default api;