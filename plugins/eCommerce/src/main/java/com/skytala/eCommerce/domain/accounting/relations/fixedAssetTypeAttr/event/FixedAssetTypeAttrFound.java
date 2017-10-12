package com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetTypeAttr.model.FixedAssetTypeAttr;
public class FixedAssetTypeAttrFound implements Event{

	private List<FixedAssetTypeAttr> fixedAssetTypeAttrs;

	public FixedAssetTypeAttrFound(List<FixedAssetTypeAttr> fixedAssetTypeAttrs) {
		this.fixedAssetTypeAttrs = fixedAssetTypeAttrs;
	}

	public List<FixedAssetTypeAttr> getFixedAssetTypeAttrs()	{
		return fixedAssetTypeAttrs;
	}

}
