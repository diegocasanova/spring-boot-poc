package sky.test.model;

public enum Category {

	SPORTS("Sports"), NEWS("News");

	private String displayName;

	Category(String displayName) {
		this.displayName = displayName;
	}

	public static Category fromString(String name) {
		for (Category category : Category.values()) {
			if (category.displayName.equals(name)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unrecognized category: " + name);
	}

}
