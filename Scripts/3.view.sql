create view vw_title
as
select tno,tname, empNo, empName, manager,salary, dept
from  employee e left join title t on e.title  = t.tno;

create view vw_department 
as
select deptNO,deptName,floor, empNo, empName, manager,salary
from  employee e left join department d on e.dept = d.deptNO;

select tno,tname, empNo, empName, manager,salary, dept from vw_title;
select deptNO,deptName,floor, empNo, empName, manager,salary from vw_department;


create view vw_full_employee
as
select  e.empNo,
e.empName,
t.tno as title_no,
t.tname as title_name,
e.manager as manager_no,
m.empName as manager_name,
e.salary,
d.deptNO as dept_no,
d.deptName as dept_name,
d.floor
from  employee e join title t on e.title  = t.tno
	 left join employee m on e.manager = m.empNo
	join department d  on e.dept  = d.deptNO;