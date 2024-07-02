import { Box, Stack, TextField, Typography, Link, Divider, Paper } from '@mui/material';
import { Alert, LoadingButton } from '@mui/lab';
import '../../assets/index.css'
import { useResponsive } from '../../hooks/use-responsive.ts';
import { useState } from 'react';
import api from '../../utils/api.ts';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useRouter } from '../../routes/hooks/use-router.ts';
import { useDispatch } from 'react-redux';
import {setToken} from '../../store/authSlice.ts'

type LoginInputs = {
  username:string,
  password:string,
}



export default function LoginView(){
  const dispatch = useDispatch();
  const lgUp = useResponsive('up','lg');
  const [isLoading,setIsLoading] = useState(false);
  const [error,setError] = useState(false);
  const [errorMessage,setErrorMessage] = useState('');

  const router = useRouter();

  const {register,handleSubmit,watch,formState:{errors}} = useForm<LoginInputs>();

  const onSubmit:SubmitHandler<LoginInputs> = async (data:LoginInputs) => {
    const response = await api.post('/login', data);
    if(response.data.code === 0){
      setError(false)
      dispatch(setToken(response.data.token))
      localStorage.setItem("token",response.data.token);
      router.push("/chat")
    }else{
      setError(true);
      setErrorMessage(response.data.message);
    }
  }



  // @ts-ignore
  return (
    <Box className="layout__root" id="root__layout">
      <header>{/*TODO*/}</header>
      <Box className="layout__main" component={'main'} display={'flex'} flexDirection={'column'} sx={{
        flex:'1 1 auto',
        ...(lgUp && {
          flexDirection:'row'
        })
      }}>
        <Box  display={'flex'} sx={{
          display:'none',
          paddingLeft:'24px',
          paddingRight:'24px',
          paddingBottom:'24px',
          width:'100%',
          maxWidth:'480px',
          position:'relative',
          paddingTop:(smartTheme) => `${smartTheme.layout.header.desktop.height}px`,/*TODO*/
          ...(lgUp && {
            display:'flex',
            gap:'64px',
            boxAlign:'center',
            alignItems:'center',
            flexDirection:'column',
            boxPack:'center',
            justifyContent:'center'
          })
        }}>
          <div>
            <Typography variant={'h3'} sx={{
              margin:'0px',
              fontWeight:700,
              fontSize:'1.5rem',
              lineHeight:1.5,
              textAlign:'center'
            }}>
              Hi, Welcome back
            </Typography>
            <Typography variant={'body1'} sx={{
              margin:'16px 0px 0px',
              fontWeight:400,
              fontHeight:'1rem',
              lineHeight:1.5,
              color:(theme) => theme.palette.text.secondary,
              textAlign:'center'
            }}>
              More effectively with optimized workflows.
            </Typography>
          </div>
          <Box component={'img'} sx={{
            width:'100%',
            aspectRatio:'4 / 3',
            objectFit:'cover',
          }} alt={"Dashboard"} src={'https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/public/assets/illustrations/illustration-dashboard.webp'}></Box>
        </Box>
        {/*css-x4f2mu*/}
        <Box className={"layout__main__content"} sx={{
          padding:'40px 40px',
          display:'flex',
          flex:'1 1 auto',
          boxAlign:'center',
          alignItems:'center',
          flexDirection:'column',
          boxPack:'center',
          justifyContent:'center',
          ...(lgUp && {
            paddingLeft:'0px',
            paddingRight:'0px',
            paddingTop:(theme) => `calc(${theme.layout.header.desktop.height + 24}px)`,
            paddingBottom:(theme) => `calc(${theme.layout.header.desktop.height + 24}px)`
          })
        }}>
          <Box sx={{
            width:'100%',
            display:'flex',
            flexDirection:'column',
            maxWidth:'420px'
          }}>
            <Stack sx={{
              display:'flex',
              flexDirection:'column',
              gap:'12px',
              marginBottom:'40px'
            }}>
              <Typography variant={'h5'} >Sign in to your account</Typography>
              <Stack direction={'row'} spacing={2}>
                <Typography variant={'body2'} color={(theme) => theme.palette.text.secondary} >Don't have an account?</Typography>
                <Link href="#" variant={'subtitle2'} sx={{
                  color:(theme) => theme.palette.primary.main
                }}>Get started</Link>
              </Stack>

              {
                error &&
                  <Alert severity={'error'}>
                    {errorMessage}
                  </Alert>
              }

            </Stack>
            <form autoCapitalize={'off'} onSubmit={handleSubmit(onSubmit)} noValidate>
              <Stack flexDirection={'column'} gap={'24px'}>
                <TextField error={errors.username !== undefined} label='Email address' fullWidth={true} InputLabelProps={{shrink:true}}
                           {...register('username',{required:true,maxLength:50})}
                  helperText={errors.username?.message}
                />
                <Stack flexDirection={"column"} gap={'12px'}>
                  <Link margin={'0px'} href={'#'} alignSelf={'flex-end'} color={'inherit'}>Forgot password?</Link>
                  <TextField error={errors.password !== undefined} InputLabelProps={{shrink:true}} type={'password'} label={"password"}
                             {...register('password',{required:true,minLength:6})}
                    helperText={errors.password?.message}
                  />
                </Stack>
                <LoadingButton type={'submit'} fullWidth={true} variant={'contained'} loading={isLoading} sx={{
                  height:'48px',
                  backgroundColor:(theme) => theme.palette.grey[800],
                  ':hover':{
                    backgroundColor:(theme) => theme.palette.grey[700]
                  }
                }}>Sign in</LoadingButton>
              </Stack>
            </form>

            <Divider sx={{
              margin:'24px 0px',
              color:(theme) => theme.palette.text.disabled
            }}>
              OR
            </Divider>
          </Box>
        </Box>
      </Box>
    </Box>
  )
}