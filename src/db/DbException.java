package db;

public class DbException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	//Passar a exceção para a super classe 
	public DbException(String msg) {
		super(msg);
	}
}
