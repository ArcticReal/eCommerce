package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Product;

public class ProductAdded implements Event{

	Product addedProduct;
	boolean success;
	
	public ProductAdded(Product addedProduct, boolean success){
		this.addedProduct = addedProduct;
		this.success = success;
	}

	public Product getAddedProduct() {
		return addedProduct;
	}

	public void setAddedProduct(Product addedProduct) {
		this.addedProduct = addedProduct;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
