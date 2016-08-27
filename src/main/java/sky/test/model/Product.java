package sky.test.model;

import java.util.Objects;

public class Product {

	private String name;
	private Category category;

	public Product() {
	};

	public Product(String name, Category category) {
		super();
		this.category = category;
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.category, this.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(this.category, other.category) && Objects.equals(this.name, other.name);
	}

}
