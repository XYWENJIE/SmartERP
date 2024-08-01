/**
 * @author huangwenjie
 * @date 2023/10/10
 */
import {forwardRef, useState} from "react";
import {Drawer} from "@mui/material";
import Scrollbar from "./Scrollbar.tsx";

const dee = {
    root:"mnl__scrollbar__root"
}

/**ye**/
const navigationBarCssProps = {
    mini:{
        root:"nav_section_mini"
    },
    horizontal:{
        root:"nav_section_horizontal"
    },
    vertical:{
        root:"nav_section_vertical"
    },
    item:{
        root:"mnl__nav__item",
        icon:"mnl__nav__item__icon",
        info:"mnl__nav__item_info",
        texts:"mnl__nav__item_texts",
        title:"mnl__nav__item_title",
        arrow:"mnl__nav__item__arrow",
        caption:"mnl__nav__item_caption"
    },
    li:"mnl__nav__li",
    ul:"mnl_nav_ul",
    paper:"mnl_nav_paper",
    subheader:"mnl_nav_subheader",
    state:{
        open:"state__open",
        active:"state__active",
        disabled:"state__disable"
    }
}

interface NavigationBarProp {
    children:React.ReactNode;
}
/*up是MUIDrawer*/
//nee是mini
const NavigationBar = forwardRef<HTMLDivElement,NavigationBarProp>(({children})=> {
    const [open,setOpen] = useState(false);
    return (
        <>
        <Scrollbar>
            <div></div>
        </Scrollbar>
        <Drawer open={open} sx={{
            ['& .MuiDrawer-paper']:{
                overflow:'unset',
            }
        }}>
            {children}
        </Drawer>
        </>
    )
});

export default NavigationBar;