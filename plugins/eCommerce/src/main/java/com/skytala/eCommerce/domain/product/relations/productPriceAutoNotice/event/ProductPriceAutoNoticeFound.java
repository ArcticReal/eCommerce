package com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.model.ProductPriceAutoNotice;
public class ProductPriceAutoNoticeFound implements Event{

	private List<ProductPriceAutoNotice> productPriceAutoNotices;

	public ProductPriceAutoNoticeFound(List<ProductPriceAutoNotice> productPriceAutoNotices) {
		this.productPriceAutoNotices = productPriceAutoNotices;
	}

	public List<ProductPriceAutoNotice> getProductPriceAutoNotices()	{
		return productPriceAutoNotices;
	}

}
