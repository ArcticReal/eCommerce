package com.skytala.eCommerce.domain.product.relations.product.event.maint;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;
public class ProductMaintFound implements Event{

	private List<ProductMaint> productMaints;

	public ProductMaintFound(List<ProductMaint> productMaints) {
		this.productMaints = productMaints;
	}

	public List<ProductMaint> getProductMaints()	{
		return productMaints;
	}

}
