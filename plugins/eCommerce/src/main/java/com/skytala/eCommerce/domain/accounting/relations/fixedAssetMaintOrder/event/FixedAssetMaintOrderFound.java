package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model.FixedAssetMaintOrder;
public class FixedAssetMaintOrderFound implements Event{

	private List<FixedAssetMaintOrder> fixedAssetMaintOrders;

	public FixedAssetMaintOrderFound(List<FixedAssetMaintOrder> fixedAssetMaintOrders) {
		this.fixedAssetMaintOrders = fixedAssetMaintOrders;
	}

	public List<FixedAssetMaintOrder> getFixedAssetMaintOrders()	{
		return fixedAssetMaintOrders;
	}

}
