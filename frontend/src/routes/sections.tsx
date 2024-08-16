import { Outlet, useRoutes } from 'react-router-dom';
import DashboardLayout from '../layouts/dashboard';
import { Suspense } from 'react';
import AppPage from '../pages/app.tsx';
import {ChatView} from "../sections/chat/view";
import LoginPage from '../pages/login.tsx';
import UserListPage from '../pages/user';

export default function Router(){
  const routes = useRoutes([
    {
      element:(
        <DashboardLayout>
          <Suspense>
            <Outlet/>
          </Suspense>
        </DashboardLayout>
      ),
      children:[
        {element:<AppPage/>,index:true},
        {path:'user',element:<UserListPage/>},
        {path:'chat',element:<ChatView/>}
      ]
    },
    {
      path:'login',
      element:<LoginPage/>
    }
  ]);
  return routes;
}