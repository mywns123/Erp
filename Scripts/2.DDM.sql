-- 내 스키마
DROP SCHEMA IF EXISTS erp;

-- 내 스키마
CREATE SCHEMA erp;

-- 직책
CREATE TABLE erp.title (
	tno   INT         NOT NULL COMMENT '직책코드', -- 직책코드
	tname VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE erp.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책코드
		);

-- 부서
CREATE TABLE erp.department (
	deptNO   INT         NOT NULL COMMENT '부서번호', -- 부서번호
	deptName VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor    INT         NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE erp.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			deptNO -- 부서번호
		);

-- 사원
CREATE TABLE erp.employee (
	empNo   INT         NOT NULL COMMENT '사원번호', -- 사원번호
	empName VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title   INT         NULL     COMMENT '직책', -- 직책
	manager INT         NULL     COMMENT '직속상사', -- 직속상사
	salary  INT         NULL     COMMENT '급여', -- 급여
	dept    INT         NULL     COMMENT '부서' -- 부서
)
COMMENT '사원';

-- 사원
ALTER TABLE erp.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			empNo -- 사원번호
		);

-- 세부정보
CREATE TABLE erp.emp_detail (
	empNo    INT        NOT NULL COMMENT '사원번호', -- 사원번호
	pic      LONGBLOB   NULL     COMMENT '증명사진', -- 증명사진
	gender   TINYINT(1) NULL     COMMENT '성별', -- 성별
	hiredate DATETIME   NULL     COMMENT '입사일', -- 입사일
	pass     CHAR(41)   NULL     COMMENT '비밀번호' -- 비밀번호
)
COMMENT '세부정보';

-- 세부정보
ALTER TABLE erp.emp_detail
	ADD CONSTRAINT PK_emp_detail -- 세부정보 기본키
		PRIMARY KEY (
			empNo -- 사원번호
		);

-- 사원
ALTER TABLE erp.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES erp.title ( -- 직책
			tno -- 직책코드
		);

-- 사원
ALTER TABLE erp.employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES erp.employee ( -- 사원
			empNo -- 사원번호
		);

-- 사원
ALTER TABLE erp.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dept -- 부서
		)
		REFERENCES erp.department ( -- 부서
			deptNO -- 부서번호
		);

-- 세부정보
ALTER TABLE erp.emp_detail
	ADD CONSTRAINT FK_employee_TO_emp_detail -- 사원 -> 세부정보
		FOREIGN KEY (
			empNo -- 사원번호
		)
		REFERENCES erp.employee ( -- 사원
			empNo -- 사원번호
		);