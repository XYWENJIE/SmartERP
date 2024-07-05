import { alpha, Theme } from '@mui/material';

interface bgBlurProps{
  color?:string,
  blur?:number,
  opacity?:number,
  imgUrl?:string
}

export const menuItem = (theme:Theme) => ({
  ...theme.typography.body2
})

export function bgBlur(props:bgBlurProps){
  const color = props?.color || '#000000';
  const blur = props?.blur || 6;
  const opacity = props?.opacity || 0.8;

  return {
    backdropFilter:`blur(${blur}px)`,
    WebkitBackdropFilter:`blur(${blur}px)`,
    backgroundColor:alpha(color,opacity)
  }
}