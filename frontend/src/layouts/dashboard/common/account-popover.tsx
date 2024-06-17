import { alpha, Avatar, Box, Divider, IconButton, MenuItem, Popover, Typography } from '@mui/material';
import { useState } from 'react';

const account = {
  displayName:'Jaydon Frankie',
  email:'xywenjie@outlook.com',
  photoURL:'/images/avatars/avatar_25.jpg'
}

const MENU_OPTIONS = [
  {
    label:'Home',
    icon:'eva:home-fill'
  }
]

export default function AccountPopover(){
  const [open,setOpen] = useState<HTMLElement | null>(null);

  const handleOpen = (event:React.MouseEvent<HTMLElement>) => {
    setOpen(event.currentTarget);
  };

  const handleClose = () => {
    setOpen(null);
  }

  return (
    <>
      <IconButton
        onClick={handleOpen}
        sx={{
          width:40,
          height:40,
          background:(theme) => alpha(theme.palette.grey[500],0.08),
          ...(open && {
            background:(theme) =>
              `linear-gradient(135deg, ${theme.palette.primary.light} 0%, ${theme.palette.primary.main} 100%)`,
          }),
        }}
      >
        <Avatar
          src={account.photoURL}
          alt={account.displayName}
          sx={{
            width:36,
            height:36,
            border:(theme) => `solid 2px ${theme.palette.background.default}`,
          }}
        >
          {account.displayName.charAt(0).toUpperCase()}
        </Avatar>
      </IconButton>
      <Popover
        open={!!open}
        anchorEl={open}
        onClose={handleClose}
        anchorOrigin={{vertical:'bottom',horizontal:'right'}}
        transformOrigin={{vertical:'top',horizontal:'right'}}
        PaperProps={{
          sx:{
            p:0,
            mt:1,
            nl:0.75,
            width:200,
          }
        }}
      >
        <Box sx={{my:1.5,px:2}}>
          <Typography noWrap variant="subtitle2">
            {account.displayName}
          </Typography>
          <Typography variant="body2" sx={{color:'text.secondary'}} noWrap>
            {account.email}
          </Typography>
        </Box>
        <Divider/>

        {MENU_OPTIONS.map((option) => (
          <MenuItem key={option.label}>
            {option.label}
          </MenuItem>
        ))}
        <MenuItem
          disableRipple
          disableTouchRipple
          onClick={handleClose}
          sx={{typography:'body2',color:'error.main',py:1.5}}
        >
          Logout
        </MenuItem>
      </Popover>
    </>
  )
}