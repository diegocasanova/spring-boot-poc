package sky.test.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import sky.test.model.Category;
import sky.test.model.Product;
import sky.test.utils.ProductUtils;

@Service
public class InMemoryCatalogueServiceImpl implements CatalogueService {

	private static final Map<String, List<Product>> CATALOGUE_BY_LOCATION;
	private static final Map<String, Product> PRODUCTS;

	static {
		Map<String, Product> products = new HashMap<>();
		products.put("1", new Product("Arsenal TV", Category.SPORTS));
		products.put("2", new Product("Chelsea TV", Category.SPORTS));
		products.put("3", new Product("Liverpool TV", Category.SPORTS));
		products.put("4", new Product("Sky News", Category.NEWS));
		products.put("5", new Product("Sky Sports News", Category.NEWS));
		PRODUCTS = Collections.unmodifiableMap(products);
	}

	static {
		Map<String, List<Product>> map = new HashMap<>();
		map.put("LON", Arrays.asList(PRODUCTS.get("1"), PRODUCTS.get("2"), PRODUCTS.get("4"), PRODUCTS.get("5")));
		map.put("LIV", Arrays.asList(PRODUCTS.get("3"), PRODUCTS.get("4"), PRODUCTS.get("5")));
		CATALOGUE_BY_LOCATION = Collections.unmodifiableMap(map);
	}

	@Override
	public Collection<Product> findProductsByLocationId(String locationId) {
		return CATALOGUE_BY_LOCATION.get(locationId);
	}

	@Override
	public Map<Category, List<Product>> findProductsByLocationIdGrouped(String locationId) {
		Collection<Product> products = findProductsByLocationId(locationId);
		return ProductUtils.groupProductsByCategory(products);
	}

}
