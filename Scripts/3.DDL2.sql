select  * from title;

-- 해당 직책을 가지고있는 사원목록을 검색
select empNo,empName
from employee e 
join title t 
 on e.title  = t.tno 
 where  tno =5;