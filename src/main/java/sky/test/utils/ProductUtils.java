package sky.test.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import sky.test.model.Category;
import sky.test.model.Product;

public class ProductUtils {
	public static Map<Category, List<Product>> groupProductsByCategory(Collection<Product> products) {
		if (CollectionUtils.isEmpty(products)) {
			return null;
		}
		return products.stream().collect(Collectors.groupingBy(Product::getCategory));
	}
}
