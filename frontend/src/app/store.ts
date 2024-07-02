import { Action, configureStore, ThunkAction } from '@reduxjs/toolkit';
import { authSlice } from '../store/authSlice.ts';

export const store  =  configureStore({
  reducer:{
    counter:authSlice.reducer
  }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>
export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, RootState, unknown, Action<string>>