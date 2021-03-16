select  * from title;

select *
  from employee
 where empno = 1003;

select empNo,empName,title_no,title_name,manager_no,manager_name,salary,dept_no,dept_name,floor
from vw_full_employee;

-- 해당 직책을 가지고있는 사원목록을 검색
select empNo,empName
from employee e 
join title t 
 on e.title  = t.tno 
 where  tno =5;
 
-- 해당 부서 소속된 사원목록을 검색
select empname, empno, deptno
  from employee e 
  join department d
    on e.dept = d.deptNo 
 where dept = 2;

-- 해당 직책별 사원수
select tname, count(*) as 사원수
  from title t left join employee e on t.tno = e.title 
 group by tno;

-- pass 길이 확인
-- 단발형 함수(Hash:MD5)
SELECT password('afdssaggdgfsf'), length(password('afdssaggdgfsf')) FROM dual;


INSERT INTO emp_detail(empno, pic, gender, hiredate, pass)VALUES(?, ?, ?, ?, ?);

select * from emp_detail;

select empNo,pic,gender,hiredate,pass from emp_detail where empno = 1003 ;

delete from emp_detail where empno = 1003;








