import { useResponsive } from '../../hooks/use-responsive.ts';
import { Box, SxProps } from '@mui/material';

interface MainPros{
  children:React.ReactNode;
  sx?:SxProps;
}

const SPACING = 8;

const Main:React.FC<MainPros> = ({children,sx,...other}) =>{
  const lgUp = useResponsive('up','lg')
  return (
    <Box
      component="main"
      sx={{
        flexGrow:1,
        mainHeight:1,
        display:'flex',
        flexDirection:'column',
        py:`${64+SPACING}px`,
        ...(lgUp && {
          px:2,
          py:`${80 + SPACING}px`,
          width:`calc(100% - ${280}px)`,
        }),
        ...sx,
      }}
      {...other}
    >
      {children}
    </Box>
  )
}

export default Main;