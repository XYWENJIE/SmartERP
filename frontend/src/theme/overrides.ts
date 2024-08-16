import { alpha } from '@mui/material/styles';
import { outlinedInputClasses } from '@mui/material/OutlinedInput';
import { LabelDisplayedRowsArgs, SxProps, Theme } from '@mui/material';

// ----------------------------------------------------------------------

const Zm = (theme:Theme):SxProps => {
  return {
    width:10,
    zIndex:9,
    padding:0,
    height:10,
    minWidth:'auto',
    '&::before, &::after':{
      content:"''",
      borderRadius:1,
      backgroundColor:theme.palette.common.white
    },
    "&.MuiBadge-invisible":{
      transform:'unset'
    }
  }
}

export function overrides(theme:any) {
  return {
    MuiCssBaseline: {
      styleOverrides: {
        '*': {
          boxSizing: 'border-box',
        },
        html: {
          margin: 0,
          padding: 0,
          width: '100%',
          height: '100%',
          WebkitOverflowScrolling: 'touch',
        },
        body: {
          margin: 0,
          padding: 0,
          width: '100%',
          height: '100%',
        },
        '#root': {
          width: '100%',
          height: '100%',
        },
        input: {
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
        img: {
          maxWidth: '100%',
          display: 'inline-block',
          verticalAlign: 'bottom',
        },
      },
    },
    MuiLink:{
      defaultProps:{
        underline:'hover'
      }
    },
    MuiBackdrop: {
      styleOverrides: {
        root: {
          backgroundColor: alpha(theme.palette.grey[900], 0.8),
        },
        invisible: {
          background: 'transparent',
        },
      },
    },
    MuiBadge:{
      variants:[
        {
          props:{variant:'online'},
          style:{
            '& .MuiBadge-badge':{
              ...Zm(theme),
              backgroundColor:theme.palette.success.main
            }
          }
        },
        {
          props: {variant: "alway"},
          style: {
            '& .MuiBadge-badge':{
              ...Zm(theme),
              backgroundColor: theme.palette.warning.main,
              '&::before':{
                width:2,
                height:4,
                transform:'translateX(1px) translateY(-1px)'
              },
              "&::after":{
                width:2,
                height:4,
                transform:"translateY(1px) rotate(125deg)"
              }
            }
          }
        },
        {
          props:{variant: "busy"},
          style:{
            "& .MuiBadge-badge":{
              ...Zm(theme),
              backgroundColor: theme.palette.error.main,
              "&::before":{
                width:6,
                height: 2
              }
            }
          }
        },
        {
          props:{variant: "offline"},
          style:{
            "& .MuiBadge-badge":{
              ...Zm(theme),
              backgroundColor: theme.palette.text.disabled,
              "&::before":{
                width:6,
                height: 2,
                borderRadius:"50%"
              }
            }
          }
        },
        {
          props:{variant: "invisible"},
          style:{
            "& .MuiBadge-badge":{
              display:'none'
            }
          }
        }
      ],
      styleOverrides:{
        dot:{
          borderRadius:'50%'
        }
      }
    },
    MuiButton: {
      styleOverrides: {
        containedInherit: {
          color: theme.palette.common.white,
          backgroundColor: theme.palette.grey[800],
          '&:hover': {
            color: theme.palette.common.white,
            backgroundColor: theme.palette.grey[800],
          },
        },
        sizeLarge: {
          minHeight: 48,
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          boxShadow: theme.customShadows.card,
          borderRadius: Number(theme.shape.borderRadius) * 2,
          position: 'relative',
          zIndex: 0, // Fix Safari overflow: hidden with border radius
        },
      },
    },
    MuiCardHeader: {
      defaultProps: {
        titleTypographyProps: { variant: 'h6' },
        subheaderTypographyProps: { variant: 'body2' },
      },
      styleOverrides: {
        root: {
          padding: theme.spacing(3, 3, 0),
        },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          [`& .${outlinedInputClasses.notchedOutline}`]: {
            borderColor: alpha(theme.palette.grey[500], 0.24),
          },
        },
      },
    },
    MuiPaper: {
      defaultProps: {
        elevation: 0,
      },
    },
    MuiTableCell: {
      styleOverrides: {
        root:{
          borderBottomStyle:"dashed"
        },
        head: {
          fontSize:14,
          color: theme.palette.text.secondary,
          fontWeight:theme.typography.fontWeightSemiBold,
          backgroundColor: theme.palette.background.neutral,
        },
        stickyHeader:{
          backgroundColor:theme.palette.background.paper
        },
        paddingCheckbox:{
          paddingLeft:theme.spacing(1)
        }
      },
    },
    MuiTableContainer:{
      styleOverrides:{
        root:{
          position:"relative",
          scrollbarWidth:"thin",
        }
      }
    },
    MuiTablePagination:{
      defaultProps:{
        getItemAriaLabel:(type:string) => type === "first" ? "第一页" : type === "last" ? "最后一页" : type === "next" ? "下一页" : "上一页",
        labelRowsPerPage:"每页行数:",
        labelDisplayedRows:({from,to,count,page}:LabelDisplayedRowsArgs)=> `第${from}条到第${to}条，${count !== -1 ? `共${count}条`:`至少${count}条`}`,
        backIconButtonProps:{
          size:"small"
        },
        nextIconButtonProps:{
          size:"small"
        },
        slotProps:{
          select:{
            name:"table-pagination-select"
          }
        }
      },
      styleOverrides:{
        root:{
          width:"100%",
        },
        toolbar:{
          height:64
        },
        actions:{
          marginRight:8
        }
      }
    },
    MuiTooltip: {
      styleOverrides: {
        tooltip: {
          backgroundColor: theme.palette.grey[800],
        },
        arrow: {
          color: theme.palette.grey[800],
        },
      },
    },
    MuiTypography: {
      styleOverrides: {
        paragraph: {
          marginBottom: theme.spacing(2),
        },
        gutterBottom: {
          marginBottom: theme.spacing(1),
        },
      },
    },
    MuiMenuItem: {
      styleOverrides: {
        root: {
          ...theme.typography.body2,
        },
      },
    },
  };
}
