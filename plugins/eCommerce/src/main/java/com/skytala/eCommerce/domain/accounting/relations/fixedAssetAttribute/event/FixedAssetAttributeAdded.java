package com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetAttribute.model.FixedAssetAttribute;
public class FixedAssetAttributeAdded implements Event{

	private FixedAssetAttribute addedFixedAssetAttribute;
	private boolean success;

	public FixedAssetAttributeAdded(FixedAssetAttribute addedFixedAssetAttribute, boolean success){
		this.addedFixedAssetAttribute = addedFixedAssetAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetAttribute getAddedFixedAssetAttribute() {
		return addedFixedAssetAttribute;
	}

}
