package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.model.FixedAssetProduct;
public class FixedAssetProductFound implements Event{

	private List<FixedAssetProduct> fixedAssetProducts;

	public FixedAssetProductFound(List<FixedAssetProduct> fixedAssetProducts) {
		this.fixedAssetProducts = fixedAssetProducts;
	}

	public List<FixedAssetProduct> getFixedAssetProducts()	{
		return fixedAssetProducts;
	}

}
