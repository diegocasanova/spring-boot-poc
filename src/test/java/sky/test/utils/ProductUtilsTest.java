package sky.test.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import sky.test.model.Category;
import sky.test.model.Product;

public class ProductUtilsTest {

	private static final Product ARSENAL_TV = new Product("Arsenal TV", Category.SPORTS);
	private static final Product SKY_NEWS = new Product("Sky News", Category.NEWS);

	@Test
	public void shouldReturnProductsGroupedByCategory() {
		List<Product> products = Arrays.asList(ARSENAL_TV, SKY_NEWS);
		Map<Category, List<Product>> actual = ProductUtils.groupProductsByCategory(products);
		assertThat(actual, not(equalTo(Collections.EMPTY_MAP)));
		assertThat(actual, hasKey(Category.SPORTS));
		assertThat(actual, hasKey(Category.NEWS));
		assertThat(actual.get(Category.SPORTS), hasItem(ARSENAL_TV));
		assertThat(actual.get(Category.NEWS), hasItem(SKY_NEWS));
	}
}
