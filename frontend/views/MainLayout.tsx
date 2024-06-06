import { AppLayout } from '@hilla/react-components/AppLayout.js';
import { DrawerToggle } from '@hilla/react-components/DrawerToggle.js';
import { useRouteMetadata } from 'Frontend/util/routing.js';
import {Suspense, useEffect, useRef} from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import {SideNav,type SideNavElement} from "@hilla/react-components/SideNav";
import {SideNavItem} from "@hilla/react-components/SideNavItem";
import {Icon} from "@hilla/react-components/Icon";
import "@vaadin/icons/";
import {patchSideNavNavigation} from "Frontend/util/side-nav-helper";

const navLinkClasses = ({ isActive }: any) => {
  return `block rounded-m p-s ${isActive ? 'bg-primary-10 text-primary' : 'text-body'}`;
};

export default function MainLayout() {
  const currentTitle = useRouteMetadata()?.title ?? 'My App';

  const sideNavRef = useRef<SideNavElement>(null);
  useEffect(() => {
    document.title = currentTitle;
  }, [currentTitle]);

    useEffect(() => {
        if(sideNavRef.current){
            //patchSideNavNavigation(sideNavRef.current);
        }
    }, [sideNavRef.current]);

  return (
    <AppLayout primarySection="drawer">
      <div slot="drawer" className="flex flex-col justify-between h-full p-m">
        <header className="flex flex-col gap-m">
          <span className="font-semibold text-l">My Application</span>
            <SideNav style={{ width: '100%' }} id="sideNav" ref={sideNavRef}>
                <SideNavItem>
                    <Icon icon="vaadin:envelope" slot="prefix" />
                    人员管理
                    <SideNavItem path="/personnel/index" slot="children">
                        <Icon icon="vaadin:inbox" slot="prefix" />
                        人员管理
                    </SideNavItem>
                    <SideNavItem path="/sent" slot="children">
                        <Icon icon="vaadin:paperplane" slot="prefix" />
                        Sent
                    </SideNavItem>
                    <SideNavItem path="/trash" slot="children">
                        <Icon icon="vaadin:trash" slot="prefix" />
                        Trash
                    </SideNavItem>
                </SideNavItem>
                <SideNavItem>
                    <Icon icon="vaadin:cog" slot="prefix" />
                    管理员
                    <SideNavItem path="/users" slot="children">
                        <Icon icon="vaadin:group" slot="prefix" />
                        登录用户
                    </SideNavItem>
                    <SideNavItem path="/permissions" slot="children">
                        <Icon icon="vaadin:key" slot="prefix" />
                        Permissions
                    </SideNavItem>
                </SideNavItem>
                <SideNavItem>
                    <Icon icon="vaadin:home" slot="prefix"/>
                    房屋租赁管理
                    <SideNavItem slot="children">
                        房源管理
                    </SideNavItem>
                    <SideNavItem slot="children">
                        租户管理
                    </SideNavItem>
                    <SideNavItem slot="children">
                        租赁合同管理
                    </SideNavItem>
                    <SideNavItem slot="children">
                        租金管理
                    </SideNavItem>
                </SideNavItem>
                <SideNavItem>
                    财务管理
                </SideNavItem>
                <SideNavItem>
                    维修与维护
                </SideNavItem>
                <SideNavItem>
                    报表与分析
                </SideNavItem>
            </SideNav>
          <nav>
            <NavLink className={navLinkClasses} to="/">
              Hello World
            </NavLink>
            <NavLink className={navLinkClasses} to="/about">
              About
            </NavLink>
              <NavLink to="人员管理"/>
          </nav>
        </header>
      </div>

      <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
      <h1 slot="navbar" className="text-l m-0">
        {currentTitle}
      </h1>

      <Suspense>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
}
