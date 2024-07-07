import { alpha, Color, Theme } from '@mui/material';

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

export const PopoverRed = (theme:Theme,color:Color) => {
  return {
    backgroundImage:'url(/assets/cyan-blur.png), url(/assets/red-blur.png)',
    backgroundRepeat:'no-repeat, no-repeat',
    backgroundPosition:"top right, left bottom",
    backgroundSize:'50% 50%',
    ...theme.direction === "rtl" && {
      backgroundPosition:"top left, right bottom",
    }
  }
}