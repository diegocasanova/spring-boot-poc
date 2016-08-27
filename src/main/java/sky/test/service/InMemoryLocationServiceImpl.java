package sky.test.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sky.test.model.Location;

@Service
public class InMemoryLocationServiceImpl implements LocationService {

	private static final Map<String, Location> LOCATIONS;

	static {
		Map<String, Location> map = new HashMap<>();
		map.put("LON", new Location("LON", "London"));
		map.put("LIV", new Location("LIV", "Liverpool"));
		LOCATIONS = Collections.unmodifiableMap(map);
	}

	@Override
	public Location findById(String id) {
		return LOCATIONS.get(id);
	}

	@Override
	public Collection<Location> findAll() {
		return LOCATIONS.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

}
