import {
    alpha,
    Autocomplete,
    Avatar,
    Badge,
    Box,
    Button, Chip,
    Container,
    IconButton,
    Input, InputAdornment, InputBase, Skeleton,
    Stack, TextField,
    Typography,
} from '@mui/material';
import { AccountCircle, ChevronLeft, PersonAdd } from '@mui/icons-material';
import theme from '../../../theme';
import Contacts from '../contacts.tsx';
import { useResponsive } from '../../../hooks/use-responsive.ts';
import { Icon } from '@iconify/react';
import { useEffect, useState } from 'react';
import api from '../../../utils/api.ts';

const top100Films = [
    {label:'The Shawshank Redemption',year:1994},
    {label:'The Shawshank Redemption',year:1994},
    {label:'The Shawshank Redemption',year:1994},
    {label:'The Shawshank Redemption',year:1994}
]

interface Contact{
    name:string;
    id:string;
    avatarUrl:string;
}

export default function ChatView(){
    const mdDown = useResponsive('down','md')
    const [contacts,setContacts] = useState<Contact[]>([]);
    useEffect(()=>{
        const conslist = async () => {
            const response = await api.get('chat/contacts');
            setContacts(response.data);
        }

        conslist();
    },[])
    return (
        <>
            <Container maxWidth={false} sx={{

            }}>
                <Typography variant="h4" sx={{
                    marginBottom:'40px'
                }}>
                    Chat
                </Typography>
                {/*主题内容*/}
                <Stack direction="row" spacing={0} useFlexGap={false} sx={{
                    boxShadow:2,
                    minHeight:'0px',
                    backgroundColor:(theme) => `${theme.palette.background.paper}`,
                    borderRadius:2,
                    flex:'1 1 0',
                    position:'relative'
                }}>
                    {/*左边状态*/}
                    <Contacts/>
                    <Stack direction="column" sx={{
                        display:'flex',
                        flex:'1 1 auto',
                        minWidth:'0px',
                    }}>
                        {/*css-1c1uhxy*/}
                        <Stack className={"css-1c1uhxy"} direction="row" spacing={0} sx={{
                            padding: '8px 8px 8px 20px',
                            height: '72px',
                            borderBottom: (theme) => `solid 1px ${theme.palette.divider}`,
                            alignItems:'Center',
                            flexShrink:0
                        }}>
                            <Typography variant="subtitle2" sx={{
                                margin:'0px 16px 0px 0px',
                                fontWeight:600,
                                fontSize:'0.875rem',
                                lineHeight:1.57143,
                                color:(theme) => theme.palette.text.primary

                            }}>To:</Typography>
                            <Autocomplete
                              options={contacts}
                              multiple={true}
                              popupIcon={null}
                              limitTags={3}
                              getOptionLabel={(option) => option.name}
                              forcePopupIcon={false}
                              renderOption={(props,option,state) => {
                                  const {key,...optionPros} = props;
                                  console.log(state)
                                  return (
                                    <li key={key} {...optionPros}>
                                        <Stack direction={"row"} sx={{
                                            mr:1,
                                            width:32,
                                            height:32,
                                            borderRadius:'50%',
                                            position:"relative"
                                        }}>
                                            <Avatar src={option.avatarUrl} sx={{
                                                width:1,
                                                height:1,
                                            }}/>
                                            <Box component={"div"}  alignItems={"center"} justifyContent={"center"}
                                                sx={{
                                                   top:0,
                                                    left:0,
                                                    width:1,
                                                    height:1,
                                                    opacity:0,
                                                    position:"absolute",
                                                    bgcolor:(theme) => alpha(theme.palette.grey[900],.8),
                                                    transition:(theme) => theme.transitions.create('all',{
                                                        easing:theme.transitions.easing.easeInOut,
                                                        duration:theme.transitions.duration.shorter
                                                    }),
                                                    ...state.selected && {
                                                        opacity:1,
                                                       color:'primary.main'
                                                    }
                                                }}>
                                                <Icon icon={"eva:checkmark-fill"}/>
                                            </Box>
                                        </Stack>
                                        {option.name}
                                    </li>
                                  )
                              }}
                              renderTags={(value,dsa) =>(
                                value.map((l,d) => <Chip {...dsa} key={l.id} label={l.name} avatar={<Avatar src={l.avatarUrl}></Avatar>} size={'small'} variant={"filled"}></Chip>)
                              )}
                              renderInput={(params) => <TextField {...params} placeholder="+ Recipients"/> }
                              sx={{
                                minWidth: { md:320 },
                                flexGrow:{xs:1,md:'unset'},
                            }}></Autocomplete>
                        </Stack>
                        {/*css-1swz2th*/}
                        <Stack direction={"row"} sx={{
                            flex:'1 1 auto',
                            minHeight:'0px',
                        }}>
                            {/*.css-uzc2y1*/}
                            <Stack direction="column" sx={{
                                flex:'1 1 auto',
                                minWidth:'0px',
                            }}>
                                <Stack direction={"column"} flexGrow={1} sx={{
                                    height:'100%',
                                    alignItems:'Center',
                                    justifyContent:'Center',
                                    paddingLeft:'24px',
                                    paddingRight:'24px'
                                }}>
                                    <Box component={"img"} src={"https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/public/assets/icons/empty/ic-chat-active.svg"}></Box>
                                    <Typography variant="h6">Good morning!</Typography>
                                    <Typography variant="caption">Write something awesome...</Typography>
                                </Stack>
                                {/*css-1pooth7*/}
                                <InputBase
                                  placeholder={"Type a message"}
                                  startAdornment={<IconButton><Icon icon="eva:smiling-face-outline" /></IconButton>}
                                  endAdornment={
                                      <Stack direction="row" sx={{
                                          flexShrink:0
                                      }}>
                                          <IconButton><Icon icon="solar:gallery-add-bold"/></IconButton>
                                          <IconButton><Icon icon="solar:paperclip-2-bold"/></IconButton>
                                          <IconButton><Icon icon="solar:microphone-2-bold"/></IconButton>
                                      </Stack>
                                  }
                                  sx={{
                                      fontWeight:400,
                                      fontSize:'1rem',
                                      boxSizing:'border-box',
                                      position:'relative',
                                      display:'inline-flex',
                                      alignItems:'center',
                                      paddingLeft:'8px',
                                      paddingRight:'8px',
                                      height:'56px',
                                      flexShrink:0,
                                      borderTop:(theme) => `solid 1px ${theme.palette.divider}`
                                  }}
                                >
                                </InputBase >
                            </Stack>
                            <Stack></Stack>
                        </Stack>
                    </Stack>
                </Stack>
            </Container>

        </>
    )
}