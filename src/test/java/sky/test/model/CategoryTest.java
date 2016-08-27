package sky.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;;

public class CategoryTest {

	@Test
	public void news() throws Exception {
		assertThat(Category.fromString("News"), equalTo(Category.NEWS));
	}

	@Test
	public void sports() throws Exception {
		assertThat(Category.fromString("Sports"), equalTo(Category.SPORTS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void other() throws Exception {
		Category.fromString("foo");
	}
}
