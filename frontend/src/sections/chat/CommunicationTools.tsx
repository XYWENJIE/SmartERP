import {
  Avatar,
  Badge,
  Box, Divider,
  IconButton,
  List,
  ListItemText,
  MenuItem,
  Paper,
  Popover,
  Stack,
  Typography,
} from '@mui/material';
import { Icon } from '@iconify/react';
import { useState } from 'react';
import { PopoverRed } from '../../theme/css.ts';

export default function CommunicationTools(){

  const [anchorEl,setAnchorEl] = useState<HTMLButtonElement | null>(null);
  const handleClick = (event:React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  }

  const handleClose = () => {
    setAnchorEl(null);
  }

  const pop = (open:boolean) => {
    return (
     <>
       <Popover open={open} anchorEl={anchorEl} onClose={handleClose} anchorOrigin={{
         horizontal:'right',
         vertical:'bottom'
       }} transformOrigin={{
         horizontal:'right',
         vertical:'top'
       }} slotProps={{
         paper:{
           sx:{
             ...PopoverRed,
             backgroundRepeat:"no-repeat, no-repeat",
            backgroundImage:"url(https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/public/assets/cyan-blur.png),url(https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/public/assets/red-blur.png)",
             backgroundPosition:"right top, left bottom",
             backgroundSize:'50%, 50%',
             padding:'4px',
             borderRadius:'10px',
             marginLeft:"6px",
             overflow:'inherit',
             ["@"]:{
               minWidth:140,
             },
             ["@ ."]:{
               gap:2
             }
           }
         }
       }} sx={{
         ...PopoverRed
       }}>
         <Box component={'span'} sx={{
           width:'14px',
           height:'14px',
           position:'absolute',
           borderBottomLeftRadius:'3.5px',
           backgroundColor:(theme)=>theme.palette.background.paper,
           border:(theme) => `solid 1px rgba(145 158 171 / 0.12)`,
           backgroundRepeat:'no-repeat',
           backgroundSize:'42px 42px',
           backgroundImage:"url(https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/public/assets/cyan-blur.png)",
           backgroundPosition:'right top',
           top:'-6.5px',
           transform:'rotate(135deg)',
           right:'17px'
         }}></Box>
         <List>
           <MenuItem><Icon icon={"solar:bell-off-bold"}/>Hide notifications</MenuItem>
           <MenuItem><Icon icon={"solar:forbidden-circle-bold"}/>Block</MenuItem>
           <MenuItem><Icon icon={"solar:danger-triangle-bold"}/>Report</MenuItem>
           <Divider/>
           <MenuItem><Icon icon={"solar:trash-bin-trash-bold"}/>Delete</MenuItem>
         </List>
       </Popover>
     </>
    )
  }

  const open = Boolean(anchorEl);
  return (
    <>
      <Stack direction={'row'} gap={2}>
        <Badge color={'success'} variant={"online"} >
          <Avatar variant={'circular'} src={'/images/avatars/avatar_25.jpg'}></Avatar>
        </Badge>
        <ListItemText aria-multiline={true}>
          <Typography variant={'body1'}>Lucian Obrien</Typography>
          <Typography variant={'body2'}>Online</Typography>
        </ListItemText>
      </Stack>
      <Stack direction={'row'} flexGrow={1} justifyContent={'flex-end'}>
        <IconButton>
          <Box component={Icon} icon={"solar:phone-bold"}></Box>
        </IconButton>
        <IconButton>
          <Box component={Icon} icon={"solar:videocamera-record-bold"}></Box>
        </IconButton>
        <IconButton>
          <Box component={Icon} icon={"ri:sidebar-fold-fill"}></Box>
        </IconButton>
        <IconButton onClick={handleClick}>
          <Box component={Icon} icon={"eva:more-vertical-fill"}></Box>
        </IconButton>
        {pop(open)}
      </Stack>
    </>
  )
}