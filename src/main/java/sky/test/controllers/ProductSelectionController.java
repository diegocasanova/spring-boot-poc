package sky.test.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sky.test.exception.CustomerLocationException;
import sky.test.model.Category;
import sky.test.model.Product;
import sky.test.service.CatalogueService;
import sky.test.service.CustomerLocationService;

@Controller
public class ProductSelectionController {

	private CatalogueService catalogueService;
	private CustomerLocationService customerLocationService;

	@Autowired
	public ProductSelectionController(CatalogueService catalogueService,
			CustomerLocationService customerLocationService) {
		super();
		this.catalogueService = catalogueService;
		this.customerLocationService = customerLocationService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showProductSelection(@CookieValue(value = "customerId", defaultValue = "1") String customerId,
			Model model) throws CustomerLocationException {
		Map<Category, List<Product>> products = catalogueService
				.findProductsByLocationIdGrouped(customerLocationService.getCustomerLocation(customerId).getId());

		model.addAttribute("productsByCategory", products);
		return "product_selection";
	}

}
