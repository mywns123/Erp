-- 계정권한 부여
	grant all
	on erp.* 
	to 'user_erp'@'localhost' identified by 'rootroot';

-- file (backup, load)권한 --root만 권한 부여가능
	grant File
	on *.*
	to 'user_erp'@'localhost';