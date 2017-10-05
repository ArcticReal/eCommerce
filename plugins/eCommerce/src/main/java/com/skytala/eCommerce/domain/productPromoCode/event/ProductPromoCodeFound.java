package com.skytala.eCommerce.domain.productPromoCode.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPromoCode.model.ProductPromoCode;
public class ProductPromoCodeFound implements Event{

	private List<ProductPromoCode> productPromoCodes;

	public ProductPromoCodeFound(List<ProductPromoCode> productPromoCodes) {
		this.productPromoCodes = productPromoCodes;
	}

	public List<ProductPromoCode> getProductPromoCodes()	{
		return productPromoCodes;
	}

}
