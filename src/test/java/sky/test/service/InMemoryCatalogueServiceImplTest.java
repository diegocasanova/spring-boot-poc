package sky.test.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sky.test.model.Category;
import sky.test.model.Product;

public class InMemoryCatalogueServiceImplTest {

	private CatalogueService catalogueService;

	private static final Product LIVERPOOL_TV = new Product("Liverpool TV", Category.SPORTS);
	private static final Product CHELSEA_TV = new Product("Chelsea TV", Category.SPORTS);
	private static final Product ARSENAL_TV = new Product("Arsenal TV", Category.SPORTS);

	private static final Product SKY_NEWS = new Product("Sky News", Category.NEWS);
	private static final Product SKY_SPORTS_NEWS = new Product("Sky Sports News", Category.NEWS);

	@Before
	public void setUp() {
		catalogueService = new InMemoryCatalogueServiceImpl();
	}

	@Test
	public void shouldAlwaysReturnSkyNewsProducts() {
		Collection<Product> lvpProducts = catalogueService.findProductsByLocationId("LIV");
		Collection<Product> lonProducts = catalogueService.findProductsByLocationId("LON");

		assertSkyNews(lonProducts);
		assertSkyNews(lvpProducts);
	}

	@Test
	public void shouldReturnArsenalTVAndChelseaTVIfLondon() {
		Collection<Product> products = catalogueService.findProductsByLocationId("LON");
		assertThat(products, not(hasSize(0)));
		assertThat(products, not(hasItem(LIVERPOOL_TV)));
		assertThat(products, hasItem(CHELSEA_TV));
		assertThat(products, hasItem(ARSENAL_TV));
	}

	@Test
	public void shouldReturnLiverpoolTVIfLiverpool() {
		Collection<Product> products = catalogueService.findProductsByLocationId("LIV");
		assertThat(products, not(hasSize(0)));
		assertThat(products, hasItem(LIVERPOOL_TV));
		assertThat(products, not(hasItem(CHELSEA_TV)));
		assertThat(products, not(hasItem(ARSENAL_TV)));
	}

	@Test
	public void shouldReturnProductsGroupedByCategoriesForLondon() {
		Map<Category, List<Product>> products = catalogueService.findProductsByLocationIdGrouped("LON");
		assertThat(products, not(equalTo(Collections.EMPTY_MAP)));
		assertThat(products, hasKey(Category.SPORTS));
		assertThat(products, hasKey(Category.NEWS));
		assertThat(products.get(Category.SPORTS), hasItem(CHELSEA_TV));
		assertSkyNews(products.get(Category.NEWS));
	}

	private void assertSkyNews(Collection<Product> products) {
		assertThat(products, not(hasSize(0)));
		assertThat(products, hasItem(SKY_NEWS));
		assertThat(products, hasItem(SKY_SPORTS_NEWS));
	}
}
