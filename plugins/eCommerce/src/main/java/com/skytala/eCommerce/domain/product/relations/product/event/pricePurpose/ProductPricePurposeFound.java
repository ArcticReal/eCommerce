package com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.pricePurpose.ProductPricePurpose;
public class ProductPricePurposeFound implements Event{

	private List<ProductPricePurpose> productPricePurposes;

	public ProductPricePurposeFound(List<ProductPricePurpose> productPricePurposes) {
		this.productPricePurposes = productPricePurposes;
	}

	public List<ProductPricePurpose> getProductPricePurposes()	{
		return productPricePurposes;
	}

}
