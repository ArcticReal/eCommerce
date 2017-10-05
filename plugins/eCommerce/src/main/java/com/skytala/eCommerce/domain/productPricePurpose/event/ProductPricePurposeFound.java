package com.skytala.eCommerce.domain.productPricePurpose.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPricePurpose.model.ProductPricePurpose;
public class ProductPricePurposeFound implements Event{

	private List<ProductPricePurpose> productPricePurposes;

	public ProductPricePurposeFound(List<ProductPricePurpose> productPricePurposes) {
		this.productPricePurposes = productPricePurposes;
	}

	public List<ProductPricePurpose> getProductPricePurposes()	{
		return productPricePurposes;
	}

}
