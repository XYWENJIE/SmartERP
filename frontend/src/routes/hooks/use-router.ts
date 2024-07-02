import { useNavigate } from 'react-router-dom';
import { useMemo } from 'react';

export function useRouter(){
  const navigate = useNavigate();

  const router = useMemo(
    ()=>({
      back:() => navigate(-1),
      forward:()=> navigate(1),
      reload:() => window.location.reload(),
      push:(href:string) => navigate(href),
      replace:(href:string) => navigate(href,{replace:true}),
    }),
    [navigate]
  );

  return router;
}