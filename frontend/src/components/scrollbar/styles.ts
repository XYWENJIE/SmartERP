import { alpha, styled, Theme } from '@mui/material';
import SimpleBar from 'simplebar-react';
import {Toaster} from "sonner";

export const StyledRootScrollbar = styled('div')(()=>({
  flexGrow:1,
  height:'100%',
  overflow:'hidden',
}))



export const StyledScrollbar = styled(SimpleBar)(({theme}) => ({
  maxHeight:'100%',
  '& .simplebar-scrollbar': {
    '&:before': {
      backgroundColor: alpha(theme.palette.grey[600], 0.48),
    },
    '&.simplebar-visible:before': {
      opacity: 1,
    },
  },
  '& .simplebar-mask': {
    zIndex: 'inherit',
  },
}));

const hexToRgba = (hexColor:string,alpha:number=1) => {
  if(hexColor.startsWith("#")){
    hexColor = hexColor.substring(1);
    if (hexColor.length === 3) {
      hexColor = hexColor.split('').map((color) => color + color).join('');
    }
    const r = parseInt(hexColor.slice(0, 2), 16);
    const g = parseInt(hexColor.slice(2, 4), 16);
    const b = parseInt(hexColor.slice(4, 6), 16);
    return `rgba(${r}, ${g}, ${b}, ${alpha})`;
  }
  return `rgba(${hexColor} / ${alpha})`
}

export const ToasterClassName = {
  root:'toaster__root',
  toast:'toaster__toast',
  title:'toaster__title',
  icon:'toaster__icon',
  iconSvg:'toaster__icon_svg',
  content:'toaster__content',
  description:'toaster_description',
  actionButton:'toaster__action__button',
  cancelButton:'toaster__cancel__button',
  closeButton:'toaster__close__button',
  loadingIcon:'toaster__loading__icon',
  default:'toaster__default',
  error:'toaster__error',
  success:'toaster__success',
  warning:'toaster__warning',
  info:'toaster_info',
  loader:'toaster-loader',
  loaderVisible:'&[data-visible="true"]',
  closeBtnVisible:'[data-close-button="true"]'
}

export const Snackbar = styled(Toaster)(({theme}) => ({
  width:300,
  [`& .${ToasterClassName.toast}`]:{
    gap:12,
    width:'100%',
    minHeight:52,
    display:'flex',
    borderRadius:12,
    alignItems:'center'
  },
  [`& .${ToasterClassName.content}`]:{
    gap:0,
    flex:'1 1 auto'
  },
  [`& .${ToasterClassName.title}`]:{
    fontSize:theme.typography.subtitle2.fontSize
  },
  [`& .${ToasterClassName.description}`]:{
    ...theme.typography.caption,
    opacity:.64
  },
  [`& .${ToasterClassName.actionButton}`]:{},
  [`& .${ToasterClassName.cancelButton}`]:{},
  [`& .${ToasterClassName.closeButton}`]:{
    top:0,
    right:0,
    left:'auto',
    color:'currentColor',
    backgroundColor:'transparent',
    transform:'translate(-6px,6px)',
    borderColor:(theme:Theme) => `rgba(${theme.palette.grey[500]},.16)`,
    transition:(theme:Theme) => theme.transitions.create(['background-color','border-color']),
    "&:hover":{
      borderColor:(theme:Theme)=>`rgba(${theme.palette.grey[500]},.24)`,
      backgroundColor:(theme:Theme) => `rgba(${theme.palette.grey[500]},.08)`
    }
  },
  [`& .${ToasterClassName.icon}`]:{
    margin:0,
    width:48,
    height:48,
    alignItems:'center',
    borderRadius:'inherit',
    justifyContent:'center',
    alignSelf:'flex-start',
    [`& .${ToasterClassName.iconSvg}`]:{
      width:24,
      height:24,
      fontSize:0
    }
  },
  [`& .${ToasterClassName.default}`]:{
    padding:theme.spacing(1,1,1,1.5),
    boxShadow:theme.customShadows.z8,
    color:'#FFFFFF',
    backgroundColor:theme.palette.text.primary,
    [`&:has(${ToasterClassName.closeBtnVisible})`]:{
      [`& .${ToasterClassName.content}`]:{
        paddingRight:32
      }
    }
  },
  [`& .${ToasterClassName.error}`]:{
    padding:theme.spacing(.5,1,.5,.5),
    boxShadow:theme.customShadows.z8,
    color:theme.palette.text.primary,
    backgroundColor:theme.palette.background.paper,
    [`& .${ToasterClassName.icon}`]:{
      color:theme.palette.error.main,
      backgroundColor:hexToRgba(theme.palette.error.main,0.08)
    }
  },
  [`& .${ToasterClassName.success}`]:{
    padding:theme.spacing(.5,1,.5,.5),
    boxShadow:theme.customShadows.z8,
    color:theme.palette.text.primary,
    backgroundColor:theme.palette.background.paper,
    [`& .${ToasterClassName.icon}`]:{
      color:theme.palette.success.main,
      backgroundColor:hexToRgba(theme.palette.success.main,0.08)
    }
  },
  [`& .${ToasterClassName.warning}`]:{
    padding:theme.spacing(.5,1,.5,.5),
    boxShadow:theme.customShadows.z8,
    color:theme.palette.text.primary,
    backgroundColor:theme.palette.background.paper,
    [`& .${ToasterClassName.icon}`]:{
      color:theme.palette.warning.main,
      backgroundColor:hexToRgba(theme.palette.warning.main,.08)
    }
  },
  [`& .${ToasterClassName.info}`]:{
    padding:theme.spacing(.5,1,.5,.5),
    boxShadow:theme.customShadows.z8,
    color:theme.palette.text.primary,
    backgroundColor:theme.palette.background.paper,
    [`& .${ToasterClassName.icon}`]:{
      color:theme.palette.info.main,
      backgroundColor:hexToRgba(theme.palette.info.main,.08)
    }
  }
}));