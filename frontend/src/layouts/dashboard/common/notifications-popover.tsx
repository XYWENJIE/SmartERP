import { useState } from 'react';
import {
  Avatar,
  Badge,
  Box, Button,
  Divider,
  IconButton,
  List, ListItemAvatar,
  ListItemButton, ListItemText,
  ListSubheader,
  Popover,
  Tooltip,
  Typography,
} from '@mui/material';
import Iconify from '../../../components/iconify/iconify.tsx';
import Scrollbar from '../../../components/scrollbar';
import { fToNow } from '../../../utils/format-time.ts';

const NOTIFICATIONS:Notification[] = [
  {
    id:'123321',
    title:'Your Order is placed',
    description:'waiting for shipping',
    avatar:null,
    type:'order_placed',
    createAt:new Date('2024-06-10 10:30:00'),
    isUnRead:true
  }
]


export default function NotificationsPopover(){
  const [notifications,setNotifications] = useState(NOTIFICATIONS);

  const totalUnRead = notifications.filter((item) => item.isUnRead === true).length;

  const [open,setOpen] = useState<HTMLElement>();

  const handleOpen = (event:React.MouseEvent<HTMLElement>) => {
    setOpen(event.currentTarget);
  }

  const handleClose = () => {
    setOpen(undefined);
  }

  const handleMarkAllAsRead = () => {
    setNotifications(
      notifications.map((notification) => ({
        ...notification,
        isUnRead:false,
      }))
    )
  }

  return (
    <>
      <IconButton color={open ? 'primary' : 'default'} onClick={handleOpen}>
        <Badge badgeContent={totalUnRead} color="error">
          <Iconify width={24} icon="solar:bell-bing-bold-duotone"/>
        </Badge>
      </IconButton>

      <Popover
      open={!!open}
      anchorEl={open}
      onClose={handleClose}
      anchorOrigin={{vertical:'bottom',horizontal:'right'}}
      transformOrigin={{vertical:'top',horizontal:'right'}}
      PaperProps={{
        sx:{
          mt:1.5,
          ml:0.75,
          width:360,
        },
      }}>
        <Box sx={{display:'flex',alignItems:'center',py:2,px:2.5}}>
          <Box sx={{flexGrow:1}}>
            <Typography variant="subtitle1">Notifications(N)</Typography>
            <Typography variant="body2" sx={{color:'text.secondary'}}>
              You have {totalUnRead} unread message
            </Typography>
          </Box>

          {totalUnRead > 0 && (
            <Tooltip title="Mark akk as read">
              <IconButton color="primary" onClick={handleMarkAllAsRead}>
                <Iconify icon="eva:done-all-fill"/>
              </IconButton>
            </Tooltip>
          )}
        </Box>

        <Divider sx={{borderStyle:'dashed'}}/>
        <Scrollbar sx={{height:{xs:340,sm:'auto'}}}>
          <List
            disablePadding
            subheader={
              <ListSubheader disableSticky sx={{py:1,px:2.5,typography:'overline'}}>
                New
              </ListSubheader>
            }>
            {notifications.slice(0,2).map((notification) => (
              <NotificationItem key={notification.id} notification={notification}/>
            ))}
          </List>
        </Scrollbar>

        <Divider sx={{borderStyle:'dashed'}}/>

        <Box sx={{p:1}}>
          <Button fullWidth disableRipple>
            View All
          </Button>
        </Box>
      </Popover>
    </>
  )
}

interface Notification{
  id:string;
  createAt:Date;
  title:string;
  description:string;
  type:string;
  isUnRead:boolean;
  avatar?:any;
}

interface NotificationItemProps{
  notification:Notification;
}

const NotificationItem:React.FC<NotificationItemProps> = ({notification}) => {

  const {avatar,title} = renderContent(notification);
  return (
    <ListItemButton
      sx={{
        py:1.5,
        px:2.5,
        mt:'1px',
        ...(notification.isUnRead && {
          bgcolor:'action.selected',
        }),
      }}
    >
      <ListItemAvatar>
        <Avatar sx={{bgcolor:'background.neutral'}}>{avatar}</Avatar>
      </ListItemAvatar>
      <ListItemText
        primary={title}
        secondary={
          <Typography
            variant="caption"
            sx={{
              mt:0.5,
              display:'flex',
              alignItems:'center',
              color:'text.disabled',
            }}
          >
            <Iconify icon="eva:clock-outline" sx={{mr:0.5,width:16,height:16}}/>
            {fToNow(notification.createAt)}
          </Typography>
        }
      />
    </ListItemButton>
  );
}

function renderContent(notification:Notification){
  const title = (
    <Typography variant="subtitle2">
      {notification.title}
      <Typography component="span" variant="body2" sx={{color:'text.secondary'}}>
        &nbsp;{notification.description}
      </Typography>
    </Typography>
  );

  return {
    avatar:notification.avatar ? <img alt={notification.title} src={notification.avatar}/> : null,
    title,
  }
}