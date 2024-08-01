import { Container } from '@mui/material';
import React from 'react';
import { Breakpoint } from '@mui/system';

interface MainContentProps{
  children:React.ReactNode;
  maxWidth?:Breakpoint|false;
}
/*Ao.content*/
const LayoutMainContent:React.FC<MainContentProps> = ({children,maxWidth='lg'}) => {
  const s = {compactLayout:false};
  return (
    <Container className={"layout__main__content"} maxWidth={maxWidth} sx={{
      display:'flex',
      flex:'1 1 auto',
      flexDirection:'column',
    }}>
      {children}
    </Container>
  )
}

export default LayoutMainContent;