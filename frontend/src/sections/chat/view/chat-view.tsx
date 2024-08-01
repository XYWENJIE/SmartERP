import {
  alpha,
  Autocomplete,
  Avatar,
  Box,
  Chip,
  IconButton,
  InputBase, LinearProgress, ListItemButton, Stack, TextField,
  Typography,
  Theme
} from '@mui/material';
import Contacts from '../contacts.tsx';
import { useResponsive } from '../../../hooks/use-responsive.ts';
import { Icon } from '@iconify/react';
import { useEffect, useState } from 'react';
import api from '../../../utils/api.ts';
import { useRouter } from '../../../routes/hooks/use-router.ts';
import { useParams, useSearchParams } from 'react-router-dom';
import CommunicationTools from '../CommunicationTools.tsx';
import SimpleBar from 'simplebar-react';
import LayoutMainContent from '../../LayoutMainContent.tsx';
import MuiSimpleBar from '../../MuiSimpleBar.tsx';



interface Contact{
    name:string;
    id:string;
    avatarUrl:string;
}

/*-----------------*/

interface Conversation{
  id:string;
  participants:Participant[];
  messages:Message[];
}

interface Message {
  body:string
}

interface Participant{
  id:string;
}

interface MessageBoxPros {
  conversation:Conversation
}

const MessageBox:React.FC<MessageBoxPros> = ({conversation}) => {
  //const userInfo = localStorage.getItem("userInfo");
  return (
    <>{conversation.messages.map((message) => {
      const me = true;
      return (
        <Stack direction={'row'} sx={{
          mb:5
        }}>
          <Avatar sx={{
            width:32,
            height:32,
            mr:2
          }}/>
          <Stack>{/*flex-start*/}
            <Typography noWrap={false} variant={'caption'} sx={{
              mb:1,
              color:'text.disabled'
            }}>Admin 3 小时</Typography>
            <Stack direction={'row'} alignContent={'center'} sx={{
              position:'relative',
              "&:hover":{
                "& .message-actions":{
                  opacity:1
                }
              }
            }}>

              <Stack sx={{
                p:1.5,
                minWidth:50,
                maxWidth:320,
                borderRadius:1,
                typography:'body2',
                backgroundColor:(theme:Theme)=> `${theme.palette.primary.light}`,
                ...me && {
                  color:'grey.800',
                  bgcolor:'primary.lighter'
                }
              }}>
                {message.body}
              </Stack>
              <Stack direction={'row'} className={'message-actions'} sx={{
                pt:.5,
                left:0,
                opacity:1,
                top:'100%',
                position:'absolute',
                transition:(theme) => theme.transitions.create(['opacity'],{
                  duration:theme.transitions.duration.shorter
                })
              }}>
                <IconButton size={'small'}><Icon icon={'solar:reply-bold'} width={16}/></IconButton>
                <IconButton size={'small'}><Icon icon={'eva:smiling-face-fill'} width={16}/></IconButton>
                <IconButton size={'small'}><Icon icon={'solar:trash-bin-trash-bold'} width={16}/></IconButton>
              </Stack>

            </Stack>
          </Stack>
        </Stack>
      )
      })}</>
  )
}

interface ListButtonPros {
  children?:React.ReactNode
}

const ListButton:React.FC<ListButtonPros> = ({children}) => (
  <ListItemButton>
    {children}
  </ListItemButton>
);

export default function ChatView(){
    const mdDown = useResponsive('down','md')
    const router = useRouter();
    const [selectedContacts,setSelectedContacts] = useState<Contact[]>([]);
    const [contacts,setContacts] = useState<Contact[]>([]);
    const [searchParams] = useSearchParams();
    const [id,setId] = useState(searchParams.get("id"));
    const [message,setMessage] = useState("");

    const [conversation,setConversation] = useState<Conversation|null>(null);
    useEffect(()=>{
        const conslist = async () => {
            const response = await api.get('/chat/contacts');
            setContacts(response.data);
        }

        conslist();
    },[])

  useEffect(() => {
    if(id){
      const loaderConversation = async () => {
        const response = await api.get(`/chat/conversation/${id}`);
        setConversation(response.data);
      }
      loaderConversation();

    }
  }, [id]);

  /**
   * 发送消息
   */
  const handleSendMessage = async () => {
      if(conversation == null && message.trim() !== ""){
          const data = {message:message,contacts:selectedContacts.map(contact => contact.id)};
          const response = await api.post("/chat/conversation",data)
        const chatId = response.data.result;
        setMessage("");
        setId(chatId);
        router.push(`/chat?id=${chatId}`)
      }else {
        console.log("fafafa");
      }
  }
  return (
        <>
          <LayoutMainContent maxWidth={false}>
            <Typography variant="h4" sx={{
              marginBottom:'40px'
            }}>
              Chat(聊天)
            </Typography>
            {/*主题内容*/}
            <Stack direction="row" spacing={0} useFlexGap={false} sx={{
              boxShadow:2,
              minHeight:'0px',
              backgroundColor:(theme) => `${theme.palette.background.paper}`,
              borderRadius:2,
              flex:'1 1 0px',
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
                  {id ? (<>
                    <CommunicationTools/>

                  </>) : (<>
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
                      onChange={(event,value,reason) => {
                        setSelectedContacts(value);
                      }}
                      limitTags={3}
                      isOptionEqualToValue={(option,value) => value.id === option.id}
                      getOptionLabel={(option) => option.name}
                      forcePopupIcon={false}
                      renderOption={(props,option,state) => {
                        // @ts-ignore
                        const {key,...optionPros} = props;
                        return (
                          <li key={key} {...optionPros}>
                            <Stack direction={"row"} sx={{
                              mr:1,
                              width:32,
                              height:32,
                              overflow:'hidden',
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
                                     transition:(theme) => theme.transitions.create(['opacity'],{
                                       easing:theme.transitions.easing.easeInOut,
                                       duration:theme.transitions.duration.shorter
                                     }),
                                     ...state.selected && {
                                       opacity:1,
                                       color:'primary.main'
                                     }
                                   }}>
                                <Box component={Icon} icon={"eva:checkmark-fill"} width={24} height={24} flexShrink={"inherit"} display={"inline-flex"}>
                                </Box>
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
                  </>)}

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
                    {id ? (<>
                      {conversation ? (<>
                        <Box flexDirection={'column'} display={'flex'} component={SimpleBar} sx={{
                          minWidth:0,
                          minHeight:0,
                          padding:"40px 24px 24px",
                          flex:'1 1 auto'
                        }}>
                          <MessageBox conversation={conversation}/>
                        </Box>
                      </>) : (<>
                        <Stack direction={'column'} sx={{
                          flex:'1 1 auto',
                          position:'relative'
                        }}>
                          <LinearProgress/>
                        </Stack>
                      </>)}

                    </>) : (<>
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
                    </>)}

                    {/*输入框*/}
                    {/*css-1pooth7*/}
                    <InputBase
                      onKeyDown={(event) => {
                        if(event.key === 'Enter'){
                          event.preventDefault();
                          handleSendMessage();
                        }
                      }}
                      onChange={(event) => setMessage(event.target.value)}
                      value={message}
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
                  {/*css-1lytpdc*/}
                  <Stack direction={'column'} minHeight={0}>
                    {conversation && (
                      <Stack sx={{
                        borderLeft:(theme) => `solid 1px ${theme.palette.divider}`,
                        width:280,
                        display:{
                          lg:'flex',
                          xs:'none'
                        },
                        flex:'1 1 auto',
                        minHeight:0,
                        transition:(theme) => theme.transitions.create(['width'],{
                          duration:theme.transitions.duration.shorter
                        })
                      }}>
                        <MuiSimpleBar>
                          <>
                            <Stack sx={{
                              alignItems:'center',
                              py:5
                            }}>
                              <Avatar src={'https://api-prod-minimal-v6.pages.dev/assets/images/avatar/avatar-2.webp'} sx={{
                                width:96,
                                height:96,
                                mb:2
                              }}></Avatar>
                              <Typography variant={'subtitle1'}>Lucina Obrien</Typography>
                              <Typography variant={'body2'} sx={{
                                color:'text.secondary',
                                mt:.5
                              }}>CTO</Typography>
                            </Stack>
                            <ListButton>Information</ListButton>
                          </>
                        </MuiSimpleBar>
                      </Stack>
                    )}
                  </Stack>
                </Stack>
              </Stack>
            </Stack>
          </LayoutMainContent>
        </>
    )
}