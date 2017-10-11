package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;
public class ProductCategoryTypeAttrFound implements Event{

	private List<ProductCategoryTypeAttr> productCategoryTypeAttrs;

	public ProductCategoryTypeAttrFound(List<ProductCategoryTypeAttr> productCategoryTypeAttrs) {
		this.productCategoryTypeAttrs = productCategoryTypeAttrs;
	}

	public List<ProductCategoryTypeAttr> getProductCategoryTypeAttrs()	{
		return productCategoryTypeAttrs;
	}

}
