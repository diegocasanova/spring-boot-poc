package sky.test.service;

import java.util.Collection;

import sky.test.model.Location;

public interface LocationService {
	Location findById(String id);

	Collection<Location> findAll();
}
