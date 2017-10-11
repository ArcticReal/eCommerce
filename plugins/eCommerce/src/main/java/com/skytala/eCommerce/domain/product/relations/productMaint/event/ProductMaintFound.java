package com.skytala.eCommerce.domain.product.relations.productMaint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMaint.model.ProductMaint;
public class ProductMaintFound implements Event{

	private List<ProductMaint> productMaints;

	public ProductMaintFound(List<ProductMaint> productMaints) {
		this.productMaints = productMaints;
	}

	public List<ProductMaint> getProductMaints()	{
		return productMaints;
	}

}
