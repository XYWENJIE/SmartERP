import { useEffect, useState } from 'react';
import { Box } from '@mui/material';
import Header from './header.tsx';
import Nav from './nav.tsx';
import Main from './main.tsx';
import { useRouter } from '../../routes/hooks/use-router.ts';
import { useAppSelector } from '../../app/hooks.ts';
import { selectToken } from '../../store/authSlice.ts';

interface DashboardLayoutProps{
  children:React.ReactNode;
}

const DashboardLayout:React.FC<DashboardLayoutProps> = ({children}) =>{
  const [openNav,setOpenNav] = useState(false);
  const router = useRouter();
  //const token = useAppSelector(selectToken);
  const token = localStorage.getItem("token");

  useEffect(() => {
    if(token === null){
      router.push("login");
    }
  }, []);

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