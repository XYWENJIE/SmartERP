import {createSlice} from '@reduxjs/toolkit';
import { RootState } from '../app/store.ts';

interface AuthState {
  token:string|null;
}

const initialState:AuthState = {
  token:null,
}

export const authSlice = createSlice({
  name:'auth',
  initialState,
  reducers:{
    setToken:(state,action)=>{
      state.token = action.payload;
    },
    removeToken:(state) => {
      state.token = null;
    }
  }
})

export const {setToken,removeToken} = authSlice.actions;

export const selectToken = (state:RootState) => state.counter.token;

export default authSlice.reducer;