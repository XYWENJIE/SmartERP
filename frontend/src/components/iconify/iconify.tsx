import { forwardRef } from 'react';
import { Box, SxProps } from '@mui/material';
import { Icon, IconifyIcon } from '@iconify/react';

interface IconifyProps{
  icon:string|IconifyIcon;
  width?:number;
  sx?:SxProps;
}

// @ts-ignore
const Iconify = forwardRef<HTMLDivElement,IconifyProps>(({icon,width = 20,sx,...other},ref) => (
  <Box
    ref={ref}
    component={Icon}
    className="component-iconify"
    icon={icon}
    sx={{width,height:width,...sx}}
    {...other}/>
))

export default Iconify;