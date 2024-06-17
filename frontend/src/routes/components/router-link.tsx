import { forwardRef } from 'react';
import { Link } from 'react-router-dom';

// ----------------------------------------------------------------------
interface RouterLinkPros{
  href:string
}

const RouterLink = forwardRef<HTMLAnchorElement,RouterLinkPros>(({ href, ...other }, ref) =>
  <Link ref={ref} to={href} {...other} />
);


export default RouterLink;