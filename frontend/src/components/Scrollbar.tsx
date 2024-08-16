import React, {forwardRef} from "react";
import {Box, SxProps} from "@mui/material";
import SimpleBar from "simplebar-react";

interface ScrollbarProps {
    children?:React.ReactNode;
    sx?:SxProps;
}

//Cs
const Scrollbar = forwardRef<HTMLDivElement,ScrollbarProps>(({children,sx,...other},ref) => {
    return (
        <Box component={SimpleBar} scrollableNodeProps={{
            ref:ref
        }} className={"mnl__scrollbar__root"} sx={{
            minWidth:0,
            minHeight:0,
            flexGrow:1,
            display:"flex",
            flexDirection:"column",
            ...sx
        }}
        {...other}>
            {children}
        </Box>
    )
});

export default Scrollbar;