import React, { useEffect } from 'react';
import { usePathname } from '../../routes';
import { alpha, Avatar, Box, Drawer, ListItemButton, Stack, Typography } from '@mui/material';
import { useResponsive } from '../../hooks/use-responsive.ts';
import Scrollbar from '../../components/scrollbar';
import SvgColor from '../../components/svg-color';
import { RouterLink } from '../../routes/components';

interface NavProps{
  openNav:boolean,
  onCloseNav:() => void;
}

const account = {
  displayName:'Jaydon Frankie',
  email:'xywenjie@outlook.com',
  photoURL:'/images/avatars/avatar_25.jpg'
}

const icon = (name:string) => (
  <SvgColor src={`/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />
);



const navConfig = [
  {
    title: 'dashboard',
    path: '/',
    icon: icon('ic_analytics'),
  },
  {
    title: 'user',
    path: '/user',
    icon: icon('ic_user'),
  },
  {
    title: 'product',
    path: '/products',
    icon: icon('ic_cart'),
  },
  {
    title: 'blog',
    path: '/blog',
    icon: icon('ic_blog'),
  },
  {
    title: 'login',
    path: '/login',
    icon: icon('ic_lock'),
  },
  {
    title: 'Not found',
    path: '/404',
    icon: icon('ic_disabled'),
  },
];

const Nav:React.FC<NavProps> = ({openNav,onCloseNav}) => {
    const pathname = usePathname();

    const upLg = useResponsive('up','lg');
  useEffect(() => {
    if(openNav){
      onCloseNav();
    }
  }, [pathname]);

  const renderAccount = (
    <Box
      sx={{
        my:3,
        mx:2.5,
        py:2,
        px:2.5,
        display:'flex',
        borderRadius:1.5,
        alignItems:'center',
        bgcolor:(theme) => alpha(theme.palette.grey[500],0.12)
      }}
    >
      <Avatar src={account.photoURL} alt="photoURL"/>
      <Box>
        <Typography variant="subtitle2">{account.displayName}</Typography>
        <Typography >Admin</Typography>
      </Box>
    </Box>
  )

  const renderMenu = (
    <Stack component="nav" spacing={0.5} sx={{px:2}}>
      {navConfig.map((item) => (
        <NavItem key={item.title} item={item}/>
      ))}
    </Stack>
  );

  const renderContent = (
    <Scrollbar
      sx={{
        height:1,
        '& .simplebar-content': {
          height: 1,
          display: 'flex',
          flexDirection: 'column',
        },
      }}
    >
      {/*Logo*/}
      {renderAccount}

      {renderMenu}
    </Scrollbar>
  );

  return (
    <Box
      sx={{
        flexShrink:{lg:0},
        width:{lg:280}
      }}>
      {upLg ? (
        <Box id={"dsadsa"}
          sx={{
            height:1,
            position:'fixed',
            width:280,
            borderRight: (theme) => `dashed 1px ${theme.palette.divider}`,
          }}
        >
          {renderContent}
        </Box>
      ):(
        <Drawer
          open={openNav}
          onClose={onCloseNav}
          PaperProps={{
            sx:{
              width:280,
            }
          }}
        >
          {renderContent}
        </Drawer>
      )}
    </Box>
  )
}

// @ts-ignore
const NavItem = ({item}) => {
  const pathname = usePathname();

  const active = item.path === pathname;

  return (
    <ListItemButton
      component={RouterLink}
      href={item.path}
      sx={{
        minHeight:44,
        borderRadius:0.75,
        typography:'body2',
        textTransform:'capitalize',
        color:'text.secondary',
        fontWeight:'fontWeightMedium',
        ...(active && {
          color:'primary.main',
          fontWeight:'fontWeightSemiBold',
          bgcolor:(theme) => alpha(theme.palette.primary.main,0.08),
          '&:hover':{
            bgcolor:(theme) => alpha(theme.palette.primary.main,0.16)
          }
        }),
      }}
    >
      <Box component="span" sx={{width:24,height:24,mr:2}}>
        {item.icon}
      </Box>
      <Box component="span">{item.title}</Box>
    </ListItemButton>
  )
}

export default Nav;