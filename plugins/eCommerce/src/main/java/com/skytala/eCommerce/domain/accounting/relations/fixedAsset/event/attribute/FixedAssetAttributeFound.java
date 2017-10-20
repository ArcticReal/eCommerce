package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.attribute.FixedAssetAttribute;
public class FixedAssetAttributeFound implements Event{

	private List<FixedAssetAttribute> fixedAssetAttributes;

	public FixedAssetAttributeFound(List<FixedAssetAttribute> fixedAssetAttributes) {
		this.fixedAssetAttributes = fixedAssetAttributes;
	}

	public List<FixedAssetAttribute> getFixedAssetAttributes()	{
		return fixedAssetAttributes;
	}

}
