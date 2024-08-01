import React, { useEffect, useState } from 'react';
import { usePathname } from '../../routes';
import { alpha, Avatar, Box, ButtonBase, Collapse, Drawer, ListItemButton, ListSubheader, Stack, styled, Theme, Typography } from '@mui/material';
import { useResponsive } from '../../hooks/use-responsive.ts';
import Scrollbar from '../../components/Scrollbar.tsx';
import SvgColor from '../../components/svg-color';
import { RouterLink } from '../../routes/components';
import { Icon } from '@iconify/react';
import navigationBarCssProps from "../../components/navigation/navigationBarCss.ts";

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
    icon: icon('ic_user'),
    children:[
      {
        title:"list",
        path:'/user'
      },
      {
        title:"create",
        path:"/user/create"
      }
    ]
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
        bgcolor:(theme:Theme) => alpha(theme.palette.grey[500],0.12)
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
    <Stack component="nav" spacing={0.5} sx={(theme:Theme) => ({
      px:2,
      ...ZZ(theme)
    })}>
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
            color:(theme:Theme) => theme.palette.text.disabled,
            padding:(theme:Theme) => theme.spacing(2,1,1,1.5),
            fontSize:(theme:Theme) => theme.typography.pxToRem(11),
            transition:(theme:Theme) => theme.transitions.create(['color','padding-left'],{
              duration:theme.transitions.duration.standard
            }),
            "&:hover":{
              pl:2,
              color:(theme:Theme) => theme.palette.text.primary,
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
              transition:(theme:Theme)=>theme.transitions.create(['opacity'],{
                     duration:theme.transitions.duration.standard
              })
            }}/>
            管理项目
          </ListSubheader>
          <Collapse in={true}>
            {navConfig.map((item) => (
              <Box 
              component={"li"} 
              className='mnl__nav__li'
              sx={{
                display:"flex",
                flexDirection:"column",
              }}
              >
                <NavItem key={item.title} item={item}/>
              </Box>
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
            borderRight: (theme:Theme) => `dashed 1px ${theme.palette.divider}`,
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

const o5 = {
  dark:"#282F37",
  light:"#EDEFF2"
}

const bw = (theme:Theme,type:string) => {
  return {
    "--nav-item-sub-active-color":theme.palette.text.primary,
    ...type === 'vertical' && {
      "--nav-item-sub-active-bg":theme.palette.action.hover,
    }
  }
}

const ZZ = (theme:Theme) => {
  return {
    ...bw(theme,"vertical"),
    "--nav-item-gap":theme.spacing(.5),
    "--nav-item-radius":`${theme.shape.borderRadius}px`,
    "--nav-item-pt":theme.spacing(.5),
    "--nav-item-pr":theme.spacing(1),
    "--nav-item-pb":theme.spacing(.5),
    "--nav-item-root-height":"44px",
    "--nav-item-sub-height":"36px",
    "--nav-item-pl":theme.spacing(1.5),
    "--nav-icon-size":"24px",
    "--nav-icon-margin":theme.spacing(0,1.5,0,0),
    "--nav-bullet-size":"12px",
    "--nav-bullet-light-color":o5.light,
    "--nav-bullet-dark-color":o5.dark
  }
}

interface ListButtonPros {
  active?:boolean;
  open?:boolean;
  depth?:number;
  component?:React.ElementType;
  href?:string,
  theme?:Theme;
}

const ListButton = styled(ListItemButton,{
  shouldForwardProp:(prop) => true
})<ListButtonPros>(({theme,depth,active,open}) => {
  const isRoot = depth === 1;
  const isSubItem = !isRoot;
  const style = {
    item:{
      width:'100%',
      paddingTop:"var(--nav-item-pt)",
      paddingLeft:"var(--nav-item-pl)",
      paddingRight:"var(--nav-item-pr)",
      paddingBottom:"var(--nav-item-pb)",
      borderRadius:"var(--nav-item-radius)",
      color:"var(--nav-item-color)",
      "&:hover":{
        backagroundColor:"var(--nav-item-hover-bg)"
      }
    }
  }
  // 源代码depth: 1才返回样式
  return {
    ...(isRoot && {
      ...ZZ(theme),
      ...style.item,
      minHeight:44,
      [`& .${navigationBarCssProps.item.icon}`]:{
      width:"",
      height:'',
      margin:""
      }
    }),
    ...(isSubItem && {
      ...style.item,
      minHeight:"var(--nav-item-sub-height)",
      "&::before":{
        left:"-11px",
        content:'""',
        position:"absolute",
        width:"var(--nav-bullet-size)",
        height:"var(--nav-bullet-size)",
        //transform:
        backgroundColor:"var(--nav-bullet-light-color)",
        mask:`url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='14' height='14' fill='none' viewBox='0 0 14 14'%3E%3Cpath d='M1 1v4a8 8 0 0 0 8 8h4' stroke='%23efefef' stroke-width='2' stroke-linecap='round'/%3E%3C/svg%3E") no-repeat 50% 50%/100% auto`
      },
      ...(active && {
        color:"var(--nav-item-sub-active-color)",
        backgroundColor:"var(--nav-item-sub-active-bg)"
      }),
    })
  }
})

// @ts-ignore
const NavItem = ({item}) => {
  const pathname = usePathname();

  const hasLink = Boolean(item.path);
  const [open,setOpen] = useState(false);

  const handleClick = () => {
      if(!hasLink){
        setOpen(!open);
      }
  }

  const active = item.path === pathname;

  return (
    <>
    <ListItemButton
      className={"mnl__nav__item"}
      component={item.path ? RouterLink : "div"}
      href={item.path}
      onClick={handleClick}
      sx={{
        minHeight:44,
        borderRadius:0.75,
        typography:'body2',
        textTransform:'capitalize',
        color:'text.secondary',
        fontWeight:'fontWeightMedium',
        ...(open && {
          color:(theme) => theme.palette.text.primary,
          backgroundColor:(theme) => theme.palette.action.selected
        }),
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
      {!hasLink && (<Box component={Icon} icon="eva:arrow-ios-forward-fill" sx={{
          width:16,
          height:16,
          flexShrink:0,
          marginLeft:"6px",
          display:"inline-flex"
        }}/>)}
      
    </ListItemButton>
    {!hasLink && (
      <Collapse  in={open} sx={{
        pl:'24px',
        ['& .mnl__nav__ul']:{
          position:'relative',
          paddingLeft:'12px',
          "&::before":{
            top:0,
            left:0,
            width:"2px",
            content:'""',
            position:'absolute',
            bottom:'11px',
            bgcolor:'#EDEFF2'
          }
        }
      }}>
  
          <Box component={"ul"} className='mnl__nav__ul' sx={{
            display:'flex',
            flexDirection:'column'
          }}>
            {item.children.map((tree:any) => (<Box component={'li'} key={tree.title} sx={{mt:1}}>
              <ListButton depth={2} className={navigationBarCssProps.item.root} component={RouterLink} active={tree.path === pathname} href={tree.path}>
                  <Box className="mnl__nav__item__texts">
                      <Box className="mnl__nav__item__title">{tree.title}</Box>
                  </Box>
              </ListButton>
            </Box>))}
          </Box>
      </Collapse>
)
}
    </>
    
  )
}

export default Nav;
