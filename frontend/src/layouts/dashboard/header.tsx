import { AppBar, Box, IconButton, Stack, Toolbar, useTheme } from '@mui/material';
import { useResponsive } from '../../hooks/use-responsive.ts';
import LanguagePopover from './common/LanguagePopover.tsx';
import NotificationsPopover from './common/notifications-popover.tsx';
import AccountPopover from './common/account-popover.tsx';
import Iconify from '../../components/iconify/iconify.tsx';
import Searchbar from './common/Searchbar.tsx';
import { bgBlur } from '../../theme/css.ts';

export interface HeaderProps{
  onOpenNav : () => void;
}

export default function Header({onOpenNav}:HeaderProps){
  const theme = useTheme();

  const lgUp = useResponsive('up', 'lg');

  const renderContent = (
    <>
      {!lgUp && (
        <IconButton onClick={onOpenNav} sx={{mr:1}}>
          <Iconify icon="eva:menu-2-fill"/>
        </IconButton>
      )}
      <Searchbar/>
      <Box sx={{flexGrow:1}} />
      <Stack direction={'row'} alignItems={'center'} spacing={1}>
        <LanguagePopover/>
        <NotificationsPopover/>
        <AccountPopover/>
      </Stack>
    </>
  )

  return (
    <AppBar
      sx={{
        boxShadow:'none',
        height:64,
        zIndex:theme.zIndex.appBar + 1,
        ...bgBlur({
          color:theme.palette.background.default,
        }),
        transition:theme.transitions.create(['height'],{
          duration:theme.transitions.duration.shorter,
        }),
        ...(lgUp && {
          width:'calc(100% - 281px)',
          height:80,
        })
      }}>
      <Toolbar
        sx={{
          height:1,
          px:{lg:5}
        }}
      >
        {renderContent}
      </Toolbar>
    </AppBar>
  )
}