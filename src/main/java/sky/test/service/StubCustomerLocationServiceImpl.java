package sky.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sky.test.exception.CustomerLocationException;
import sky.test.model.Location;

@Service
public class StubCustomerLocationServiceImpl implements CustomerLocationService {

	private LocationService locationService;

	@Autowired
	public StubCustomerLocationServiceImpl(LocationService locationService) {
		super();
		this.locationService = locationService;
	}

	@Override
	public Location getCustomerLocation(String customerId) throws CustomerLocationException {
		switch (customerId) {
		case "1":
			return locationService.findById("LON");
		case "2":
			return locationService.findById("LVP");

		}
		throw new CustomerLocationException(customerId);
	}

}
