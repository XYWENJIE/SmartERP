import React, { useEffect, useState } from 'react';
import { usePathname } from '../../routes';
import { alpha, Avatar, Box, Collapse, Drawer, ListItemButton, ListSubheader, Stack, Typography } from '@mui/material';
import { useResponsive } from '../../hooks/use-responsive.ts';
import Scrollbar from '../../components/scrollbar';
import SvgColor from '../../components/svg-color';
import { RouterLink } from '../../routes/components';
import { Icon } from '@iconify/react';
import theme from '../../theme';
import Iconify from '../../components/iconify/iconify.tsx';

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
    title:'chat',
    path:"/chat",
    icon:icon('ic_blog')
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

const M0 = {
  root:'mnl__icon__root'
}

const Nav:React.FC<NavProps> = ({openNav,onCloseNav}) => {
    const pathname = usePathname();
    const [open,setOpen] = useState(true);

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

  /*管理项目*/
  const renderMenu = (
    <Stack component="nav" spacing={0.5} sx={{px:2}}>
      <Box className={"mnl__nav__ul"} key={"Overview"} component={"ul"} display={'flex'} flexDirection={'column'} gap={"4px"} sx={{
      }}>
        <Box component={"li"} className={"mnl__nav__li"}>
          <ListSubheader component={"div"} disableSticky={true} sx={{
            gap:1,
            cursor:'pointer',
            position:'relative',
            typography:'overline',
             backgroundColor:'transparent',
            alignContent:'center',
            fontWeight:700,
            color:(theme) => theme.palette.text.disabled,
            padding:(theme) => theme.spacing(2,1,1,1.5),
            fontSize:(theme) => theme.typography.pxToRem(11),
            transition:(theme) => theme.transitions.create(['color','padding-left'],{
              duration:theme.transitions.duration.standard
            }),
            "&:hover":{
              pl:2,
              color:(theme) => theme.palette.text.primary,
              ['& .mnl__icon__root']:{
                opacity:1
              }
            }
          }}>
            <Box component={Icon}
                 className={"mnl__icon__root"}
                 width={16}
                 icon={open ? "eva:arrow-ios-downward-fill" : "eva:arrow-ios-forward-fill"} sx={{
                   left:-4, position:'absolute',
              opacity:0,
              transition:(theme)=>theme.transitions.create(['opacity'],{
                     duration:theme.transitions.duration.standard
              })
            }}/>
            管理项目
          </ListSubheader>
          <Collapse in={true}>
            {navConfig.map((item) => (
              <NavItem key={item.title} item={item}/>
            ))}
          </Collapse>
        </Box>
      </Box>
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