package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model.ProductCalculatedInfo;
public class ProductCalculatedInfoFound implements Event{

	private List<ProductCalculatedInfo> productCalculatedInfos;

	public ProductCalculatedInfoFound(List<ProductCalculatedInfo> productCalculatedInfos) {
		this.productCalculatedInfos = productCalculatedInfos;
	}

	public List<ProductCalculatedInfo> getProductCalculatedInfos()	{
		return productCalculatedInfos;
	}

}
