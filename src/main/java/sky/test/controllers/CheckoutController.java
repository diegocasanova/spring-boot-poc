package sky.test.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import sky.test.model.Category;
import sky.test.model.Product;
import sky.test.utils.ProductUtils;

@Controller
@Scope("session")
public class CheckoutController {

	private Product[] selection;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void checkout(@CookieValue(value = "customerId", defaultValue = "1") String customerId,
			@RequestBody Product[] products, Model model) {
		selection = products;
		// do something with the selection
	}

	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String confirmation(@CookieValue(value = "customerId", defaultValue = "1") String customerId, Model model) {
		Map<Category, List<Product>> products = ProductUtils.groupProductsByCategory(Arrays.asList(selection));
		model.addAttribute("productsByCategory", products);
		return "confirmation";
	}

}
