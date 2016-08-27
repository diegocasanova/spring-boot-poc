package sky.test.service;

import sky.test.exception.CustomerLocationException;
import sky.test.model.Location;

public interface CustomerLocationService {
	Location getCustomerLocation(String customerId) throws CustomerLocationException;
}
