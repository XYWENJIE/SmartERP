import ContactsView from "Frontend/views/contacts/ContactsView";
import MainLayout from "Frontend/views/MainLayout";
import LoginView from "Frontend/views/LoginView";
import RegisterView from "Frontend/views/RegisterView";
import {createBrowserRouter,RouteObject} from "react-router-dom";
import PersonnelView from "Frontend/views/personnel/PersonnelView";
export const routes = [
    {
        element: <MainLayout/>,
        handle:{title:'Hilla CRM'},
        children:[
            {path:'/',element:<ContactsView/>,handle:{title:'Contacts'}},
            {path:'/personnel/index',element:<PersonnelView/>,handle:{title: "Personnel View"}}
        ]
    },
    {
        path:'/login',
        element:<LoginView/>
    },
    {
        path:'/register',
        element:<RegisterView/>
    }
] as RouteObject[]

export default createBrowserRouter(routes)