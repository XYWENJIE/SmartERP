import {Toaster, ToasterProps} from "sonner";
import {Theme} from "@mui/material";

const useStyles = (theme:Theme) => ({
    width:300,
})

export default function MyToaster(pros:ToasterProps,MuiTheme:Theme){
    const classes = useStyles(MuiTheme)
    return <Toaster {...pros}></Toaster>
}