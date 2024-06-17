import { Outlet, useRoutes } from 'react-router-dom';
import DashboardLayout from '../layouts/dashboard';
import { Suspense } from 'react';
import AppPage from '../pages/app.tsx';

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
        {path:'user',element:<div>user</div>}
      ]
    }
  ]);
  return routes;
}