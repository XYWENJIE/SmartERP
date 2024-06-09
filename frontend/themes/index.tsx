import {useMemo} from "react";
import CssBaseline from "@mui/material/CssBaseline";
import {createTheme,ThemeProvider as MuiThemeProvider} from "@mui/material";
import {palette} from "Frontend/themes/palette";
import PropTypes from "prop-types";

type Props = {
    children:React.ReactNode;
}

export default function ThemeProvider({children}:Props){
    const memoizedValue = useMemo(()=>({
        palette:palette(),
    }),[]);

    const theme = createTheme(memoizedValue);

    theme.components = overrides(theme);
    return (<MuiThemeProvider theme={theme}>
        <CssBaseline/>
        {children}
    </MuiThemeProvider>);
}

ThemeProvider.prototype = {
    children:PropTypes.node,
};