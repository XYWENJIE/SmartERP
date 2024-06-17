import { useState } from 'react';
import { Box } from '@mui/material';
import Header from './header.tsx';
import Nav from './nav.tsx';
import Main from './main.tsx';

interface DashboardLayoutProps{
  children:React.ReactNode;
}

const DashboardLayout:React.FC<DashboardLayoutProps> = ({children}) =>{
  const [openNav,setOpenNav] = useState(false);

  return (
    <>
      <Header onOpenNav={()=> setOpenNav(true)}/>
      <Box
        sx={{
          minHeight:1,
          display:'flex',
          flexDirection:{xs:'column',lg:'row'},
        }}
      >
        <Nav openNav={openNav} onCloseNav={() => setOpenNav(false)}></Nav>

        <Main>{children}</Main>
      </Box>
    </>
  );
}

export default DashboardLayout;