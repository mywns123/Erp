-- erp.emp_detail definition

CREATE TABLE `emp_detail` (
  `empNo` int(11) NOT NULL COMMENT '사원번호',
  `pic` longblob COMMENT '증명사진',
  `gender` tinyint(1) DEFAULT NULL COMMENT '성별',
  `hiredate` date DEFAULT NULL COMMENT '입사일',
  `pass` char(41) DEFAULT NULL COMMENT '비밀번호',
  PRIMARY KEY (`empNo`),
  CONSTRAINT `FK_employee_TO_emp_detail` FOREIGN KEY (`empNo`) REFERENCES `employee` (`empNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='세부정보';

select * from emp_detail;

