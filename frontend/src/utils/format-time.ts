import {formatDistanceToNow} from 'date-fns';

export function fToNow(date:any){
  return date ? formatDistanceToNow(new Date(date),{
    addSuffix:true,
  }) : '';
}