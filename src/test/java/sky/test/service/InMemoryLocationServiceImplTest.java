package sky.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import sky.test.model.Location;

public class InMemoryLocationServiceImplTest {

	private static final Location LONDON = new Location("LON", "London");
	private static final Location LIVERPOOL = new Location("LIV", "Liverpool");

	private LocationService locationService;

	@Before
	public void setUp() {
		locationService = new InMemoryLocationServiceImpl();
	}

	@Test
	public void shouldReturnLondon() {
		Location actual = locationService.findById("LON");
		assertThat(actual, equalTo(LONDON));
	}

	@Test
	public void shouldReturnLondonAndLiverpool() {
		Collection<Location> actual = locationService.findAll();
		assertThat(actual, not(hasSize(0)));
		assertThat(actual, hasItem(LONDON));
		assertThat(actual, hasItem(LIVERPOOL));
	}
}
