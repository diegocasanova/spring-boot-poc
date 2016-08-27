package sky.test.exception;

public class CustomerLocationException extends Exception {

	private static final long serialVersionUID = -7357649461142084736L;

	public CustomerLocationException(String customerId) {
		super(String.format("There was a problem retrieving information for customer [%s].", customerId));
	}

}
