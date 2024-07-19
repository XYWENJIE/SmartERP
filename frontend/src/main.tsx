import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import {store} from './app/store.ts';
import {Toaster} from 'sonner';
import MyToaster from "./utils/MyToaster.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <HelmetProvider>
    <BrowserRouter>
      <React.StrictMode>
        <Provider store={store}>
          <App />
            {/*<Toaster position={'top-right'}*/}
            {/*         expand={true}*/}
            {/*         gap={12}*/}
            {/*         closeButton={true}*/}
            {/*         offset={16}*/}
            {/*         visibleToasts={4}*/}
            {/*         className={"toaster__root"}*/}
            {/*         toastOptions={{*/}
            {/*            unstyled:true,*/}
            {/*            classNames:{*/}
            {/*                description:'MuiSnackbarContent-message'*/}
            {/*            }*/}
            {/*}}/>*/}
        </Provider>
      </React.StrictMode>
    </BrowserRouter>
  </HelmetProvider>

)
