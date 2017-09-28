package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.ProductCategory;

public class ProductCategoryFound implements Event {

	private List<ProductCategory> productCategorys;

	public ProductCategoryFound(List<ProductCategory> productCategorys) {
		this.setProductCategorys(productCategorys);
	}

	public List<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(List<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}
}
