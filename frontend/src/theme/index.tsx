import { ThemeProvider as MUIThemeProvider } from '@mui/material/styles';
import { createTheme, CssBaseline } from '@mui/material';
import React, { useMemo } from 'react';
import { overrides } from './overrides.ts';
import { palette } from './palette.ts';
import { typography } from './typography.ts';
import { shadows } from './shadows.ts';
import { customShadows } from './custom-shadows.ts';

interface ThemeProviderProps {
  children:React.ReactNode;
}

declare module '@mui/material/Badge' {
  interface BadgePropsVariantOverrides {
    online:true
  }
}

declare module '@mui/material/styles' {
  interface Theme {
    layout:{
      header:{
        desktop:{
          height:number
        }
      }
    },
    customShadows:{
      z8:string
    }
  }

  interface ThemeOptions {
    layout:{
      header:{
        desktop:{
          height:number
        }
      }
    },
    customShadows:{
      z8:string
    }
  }

  interface BadgeVariants{
    online:React.CSSProperties
  }
}

const ThemeProvider:React.FC<ThemeProviderProps> = ({children}) =>{

  const memoizedValue = useMemo(()=>({
    layout:{
      header:{
        desktop:{
          height:72
        }
      }
    },
    palette:palette(),
    typography,
    shadows:shadows(),
    customShadows:customShadows(),
    shape:{borderRadius:8},
  }),
    []
  );

  // @ts-ignore
  const theme = createTheme(memoizedValue);

  // @ts-ignore
  theme.components = overrides(theme);

  return (
    <MUIThemeProvider theme={theme}>
      <CssBaseline/>
      {children}
    </MUIThemeProvider>
  );
}

export default ThemeProvider;