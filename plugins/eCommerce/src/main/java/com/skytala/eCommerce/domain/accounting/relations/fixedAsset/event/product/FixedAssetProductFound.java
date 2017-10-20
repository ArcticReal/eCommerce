package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.product;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.product.FixedAssetProduct;
public class FixedAssetProductFound implements Event{

	private List<FixedAssetProduct> fixedAssetProducts;

	public FixedAssetProductFound(List<FixedAssetProduct> fixedAssetProducts) {
		this.fixedAssetProducts = fixedAssetProducts;
	}

	public List<FixedAssetProduct> getFixedAssetProducts()	{
		return fixedAssetProducts;
	}

}
