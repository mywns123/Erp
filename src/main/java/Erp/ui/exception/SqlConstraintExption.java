package Erp.ui.exception;

@SuppressWarnings("serial")
public class SqlConstraintExption extends RuntimeException {

	public SqlConstraintExption() {
		super("참조하는 레코드가 존재합니다.");
	}

	public SqlConstraintExption(String message) {
		super(message);
	}

	public SqlConstraintExption(Throwable cause) {
		super("참조하는 레코드가 존재합니다.",cause);
	}

	public SqlConstraintExption(String message, Throwable cause) {
		super(message, cause);
	}

	
}
