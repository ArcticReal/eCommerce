package com.skytala.eCommerce.domain.product.relations.productTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productTypeAttr.model.ProductTypeAttr;
public class ProductTypeAttrFound implements Event{

	private List<ProductTypeAttr> productTypeAttrs;

	public ProductTypeAttrFound(List<ProductTypeAttr> productTypeAttrs) {
		this.productTypeAttrs = productTypeAttrs;
	}

	public List<ProductTypeAttr> getProductTypeAttrs()	{
		return productTypeAttrs;
	}

}
