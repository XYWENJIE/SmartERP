import {alpha} from "@mui/material/styles";
import {outlinedInputClasses} from "@mui/material";

// -------------------------------------------------------

export function overrides(theme){
    return {
        MuiThemeProvider: {
            styleOverrides:{
                '*':{
                    boxSizing: 'border-box',
                },
                html:{
                    margin:0,
                    padding:0,
                    width:'100%',
                    height:'100%',
                    WebkitOverflowScrolling:'touch'
                },
                body:{
                    margin:0,
                    padding:0,
                    width:'100%',
                    height:'100%',
                },
                '#root':{
                    width:'100%',
                    height:'100%',
                },
                input :{
                    '&[type=number]': {
                        MozAppearance: 'textfield',
                        '&::-webkit-outer-spin-button': {
                            margin: 0,
                            WebkitAppearance: 'none',
                        },
                        '&::-webkit-inner-spin-button': {
                            margin: 0,
                            WebkitAppearance: 'none',
                        },
                    },
                },
                img:{
                    maxWidth: '100%',
                    display: 'inline-block',
                    verticalAlign: 'bottom',
                },
            },
        },
        MuiBackdrop:{
            styleOverrides:{
                root:{
                    backgroundColor: alpha(theme.palette.grey[900], 0.8),
                },
                invisible:{
                    background: 'transparent',
                }
            }
        },
        MuiButton:{
            styleOverrides:{
                containedInherit:{
                    color: theme.palette.common.white,
                    backgroundColor: theme.palette.grey[800],
                    '&:hover': {
                        color: theme.palette.common.white,
                        backgroundColor: theme.palette.grey[800],
                    },
                },
            },
            sizeLarge:{
                minHeight:48,
            }
        }
    }
}