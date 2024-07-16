import SimpleBar from 'simplebar-react';
import { Box } from '@mui/material';

interface MuiSimpleBarProps {
  children?:React.ReactNode;
}

const MuiSimpleBar:React.FC<MuiSimpleBarProps> = ({children}) => {
  return (
    <Box component={SimpleBar} flexGrow={1} display={'flex'} flexDirection={'column'} paddingBottom={'8px'}>
      {children}
    </Box>
  )
}

export  default MuiSimpleBar;