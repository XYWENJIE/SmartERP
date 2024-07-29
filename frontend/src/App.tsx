import './App.css'
import ThemeProvider from './theme';
import Router from './routes/sections.tsx';
import { useScrollToTop } from './hooks/use-scroll-to-top.ts';
import MyToaster from "./utils/MyToaster.tsx";
import React from "react";
import {Snackbar, ToasterClassName} from "./components/scrollbar/styles.ts";
import {Toaster} from "sonner";
import {Icon} from "@iconify/react";

function App() {

  useScrollToTop();

  return (
    <ThemeProvider>
      <Router/>
        <Snackbar
            expand={true}
            gap={12}
            offset={16}
            visibleToasts={4}
            position={'top-right'}
            className={ToasterClassName.root}
            closeButton={true}
            duration={20000}
            toastOptions={{
              unstyled:true,
              classNames:{
                  toast:ToasterClassName.toast,
                  icon:ToasterClassName.icon,
                  content:ToasterClassName.content,
                  title:ToasterClassName.title,
                  description:ToasterClassName.description,
                  actionButton:ToasterClassName.actionButton,
                  cancelButton:ToasterClassName.cancelButton,
                  closeButton:ToasterClassName.closeButton,
                  default:ToasterClassName.default,
                  info:ToasterClassName.info,
                  error:ToasterClassName.error,
                  success:ToasterClassName.success,
                  warning:ToasterClassName.warning
              }
            }}
            icons={{
                loading:<span className={ToasterClassName.loadingIcon}></span>,
                info:<Icon className={ToasterClassName.iconSvg} icon={"solar:info-circle-bold"}></Icon>,
                success:<Icon className={ToasterClassName.iconSvg} icon={"solar:check-circle-bold"}></Icon>,
                warning:<Icon className={ToasterClassName.iconSvg} icon={"solar:danger-triangle-bold"}></Icon>,
                error:<Icon className={ToasterClassName.iconSvg} icon={'solar:danger-bold'}></Icon>
            }}
        />
    </ThemeProvider>
  )
}

export default App
