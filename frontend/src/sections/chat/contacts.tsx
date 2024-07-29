import {
  alpha,
  Avatar,
  Badge,
  Box,
  ButtonBase,
  IconButton,
  InputAdornment, ListItemButton, ListItemText,
  Skeleton,
  Stack,
  TextField, Typography,
} from '@mui/material';
import { AccountCircle, ChevronLeft, PersonAdd } from '@mui/icons-material';
import { useResponsive } from '../../hooks/use-responsive.ts';
import { Icon } from '@iconify/react';
import { useEffect, useMemo, useState } from 'react';
import api from '../../utils/api.ts';
import SimpleBar from 'simplebar-react';
import MuiSimpleBar from '../MuiSimpleBar.tsx';

interface ContactsProps{
  id:string;
  name:string;
}


export default function Contacts(){
  const [loadProcess,setLoadProcess] = useState(true);
  const [contacts,setContacts] = useState<ContactsProps[]>([])
  const lgUp = useResponsive('down','md');
  const rowCount = 8;

  useEffect(()=>{
      const conslist = async () => {
        const response = await api.get('chat/contacts');
        setContacts(response.data);
        setLoadProcess(false);
      }

      conslist();
  },[])

  const skeletons = Array.from({length:rowCount}).map(()=>(
    <Stack direction="row" sx={{
      gap:'16px',
      display:'flex',
      alignItems:'center',
      padding:'12px 20px'
    }}>
      <Skeleton variant="circular" animation="wave" sx={{
        width:"48px",
        height:'48px',
        backgroundColor:(theme) => alpha(theme.palette.grey[400],0.12),
        borderRadius:'50%'
      }}></Skeleton>
      <Stack direction="column" flexGrow={1} gap={"8px"}>
        <Skeleton variant="rounded" animation="wave" sx={{
          backgroundColor:(theme) => alpha(theme.palette.grey[400],0.12),
          width:'75%',
          height:'10px',
          borderRadius:'16px'
        }}></Skeleton>
        <Skeleton variant="rounded" animation="wave" sx={{
          backgroundColor:(theme) => alpha(theme.palette.grey[400],0.12),
          width:'50%',
          height:'10px',
          borderRadius:"16px"
        }}></Skeleton>
      </Stack>
    </Stack>
  ));

  const contactsList = useMemo(()=>(
    <Box>
      <Box >
        {contacts.map((contact) => (
          <ListItemButton key={contact.id} sx={{
            gap:'16px'
          }}>
            <Badge color={"primary"} variant={"dot"}><Avatar src={'https://api-prod-minimal-v6.pages.dev/assets/images/avatar/avatar-2.webp'}/></Badge>
            <ListItemText>
              <Typography variant={"subtitle2"}>{contact.name}</Typography>
              <Typography noWrap={true} variant={"body2"} sx={{
                color:(theme) => theme.palette.text.secondary,
              }}>
                You: The waves crashed against the shore, creating a soothing symphony of sound.
              </Typography>
            </ListItemText>
            <Stack>
              <Typography noWrap={true} variant={"body2"} sx={{
                margin:'0px 0px 12px',
                color:(theme) => theme.palette.text.secondary,
                fontSize:'12px'
              }}>a day</Typography>
            </Stack>
          </ListItemButton >
        ))}
      </Box>
    </Box>
  ),[contacts])

  return (
    <>
      <Box sx={{
        display:'flex',
        flexDirection:"columns",
        ...(lgUp && {}),
      }}>
        <ButtonBase sx={{
          display: 'none',
          ...(lgUp && {
            display: 'inline-flex',
            top: '84px',
            left: '0px',
            zIndex: 9,
            width: '32px',
            height: '32px',
            position: 'absolute',
            borderRadius:'0px 12px 12px 0px',
            boxShadow:1,
            color:(theme) => `${theme.palette.primary.contrastText}`,
            backgroundColor:(theme) => `${theme.palette.primary.main}`,
            transition:(theme) =>
              theme.transitions.create('all',{
                duration: theme.transitions.duration.shorter,
              })
          })
        }}>
          <Icon icon="mdi-light:home"/>
        </ButtonBase>
        <Stack direction={"column"} sx={{
          width:'320px',
          borderRight:(theme) => `solid 1px ${theme.palette.divider}`,
          transition:(theme) =>
            theme.transitions.create('width',{
              duration: theme.transitions.duration.shorter,
            }),
          ...(lgUp && {
            display:'none'
          })
        }} >
          <Stack direction="row" spacing={4} sx={{
            padding:'20px 20px 0px',
          }}>
            <Badge color="success" variant="online" anchorOrigin={{
                vertical:'bottom',
                horizontal:'right'
              }}>
              <Avatar src="/images/avatars/avatar_25.jpg" sx={{
                width:'48px',
                height:'48px'
              }}/>
            </Badge>
            <Box sx={{
              flexGrow:1
            }}></Box>
            <IconButton sx={{
              padding:'8px'
            }}>
              <ChevronLeft />
            </IconButton>
            <IconButton>
              <PersonAdd/>
            </IconButton>
          </Stack>
          <Box sx={{
            padding:'0px 20px 20px'
          }}>
            <TextField fullWidth={true}
                       placeholder={"查询联系人"}
                       sx={{
                          margin:"20px 0px 0px"
                        }}
                       InputProps={{
                         startAdornment:(
                           <InputAdornment position="start">
                             <AccountCircle/>
                           </InputAdornment>
                         )
                       }}>
            </TextField>
          </Box>
          {loadProcess ? skeletons : <MuiSimpleBar>{contactsList}</MuiSimpleBar>}

        </Stack>
      </Box>
    </>
  );
}

/*<SimpleBarWrapper>{contactsList}</SimpleBarWrapper>*/