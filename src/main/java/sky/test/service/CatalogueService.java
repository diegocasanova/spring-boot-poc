package sky.test.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import sky.test.model.Category;
import sky.test.model.Product;

public interface CatalogueService {
	public Collection<Product> findProductsByLocationId(String locationId);

	public Map<Category, List<Product>> findProductsByLocationIdGrouped(String locationId);
}
