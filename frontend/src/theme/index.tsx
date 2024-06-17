import {ThemeProvider as MUIThemeProvider} from '@mui/material/styles';
import { createTheme, CssBaseline } from '@mui/material';
import { useMemo } from 'react';
import { overrides } from './overrides.ts';
import { palette } from './palette.ts';
import { typography } from './typography.ts';
import { shadows } from './shadows.ts';
import { customShadows } from './custom-shadows.ts';

interface ThemeProviderProps {
  children:React.ReactNode;
}

const ThemeProvider:React.FC<ThemeProviderProps> = ({children}) =>{

  const memoizedValue = useMemo(()=>({
    palette:palette(),
    typography,
    shadows:shadows(),
    customShadows:customShadows(),
    shape:{borderRadius:8}
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