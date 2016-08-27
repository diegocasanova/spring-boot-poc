var Basket = (function() {
	var _items = [];
	var _container;
	var _checkoutBtn;
	var _self = this;
	
	return {
		addItem : function(item) {
			_items.push(item);
			var element = "<div class=\"basket-product\"><input type=\"hidden\" value="
					+ item.category
					+ "></input><label>"
					+ item.name
					+ "</label></div>";
			_container.append(element);
			_checkoutBtn.prop('disabled', false);
		},
		removeItem : function(item) {
			_items = _items.filter(function(element) {
				return element.name !== item.name;
			});
			_container.find("input:hidden").filter(function() {
				return this.value == item.category;
			}).remove();
			_container.find("label").filter(function() {
				return this.firstChild.nodeValue.trim() === item.name;
			}).remove();
			if (_items.length == 0) {
				_checkoutBtn.prop('disabled', true);
			}
		},
		checkout : function() {
			$.ajax({
				url : "checkout",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				dataType : "html",
				data : JSON.stringify(_items),
				success : function(data) {
					window.location.href = "confirmation";
				}
			});
		},
		init : function(element, button) {
			_container = element;
			_checkoutBtn = button;
		}

	};
})();

$(function() {

	var basket = $(".basket-selection");
	var form = $("#basket");
	var checkboxs = $(".checkbox");
	var btn = $(".btn-checkout");

	Basket.init(basket, btn);

	// form submit
	form.submit(function(e) {
		Basket.checkout();
		e.preventDefault();
	});

	// reset the checkboxs
	checkboxs.prop("checked", false);

	// on checkbox changes add/remove items
	checkboxs.change(function() {
		var name = $(this).next().text();
		var category = $(this).attr("value");
		var product = {
			name : name,
			category : category
		};
		if (this.checked) {
			Basket.addItem(product);
		} else {
			Basket.removeItem(product);
		}
	});

});