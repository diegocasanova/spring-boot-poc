package sky.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import sky.test.exception.CustomerLocationException;
import sky.test.model.Location;

@RunWith(MockitoJUnitRunner.class)
public class StubCustomerLocationServiceImplTest {

	private CustomerLocationService customerLocationService;

	@Mock
	private LocationService locationService;

	@Before
	public void setUp() {
		locationService = mock(LocationService.class);
		customerLocationService = new StubCustomerLocationServiceImpl(locationService);
	}

	@Test
	public void shouldReturnLondonForCustomer1() throws Exception {
		when(locationService.findById("LON")).thenReturn(new Location("LON", "London"));
		Location london = customerLocationService.getCustomerLocation("1");
		assertThat(london.getName(), equalTo("London"));
	}

	@Test(expected = CustomerLocationException.class)
	public void shouldThrowExceptionForUnknownCustomer() throws Exception {
		customerLocationService.getCustomerLocation("3");
	}
}
