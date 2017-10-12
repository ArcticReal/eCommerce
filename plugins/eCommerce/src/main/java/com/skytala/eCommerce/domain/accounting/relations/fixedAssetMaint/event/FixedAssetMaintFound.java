package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.model.FixedAssetMaint;
public class FixedAssetMaintFound implements Event{

	private List<FixedAssetMaint> fixedAssetMaints;

	public FixedAssetMaintFound(List<FixedAssetMaint> fixedAssetMaints) {
		this.fixedAssetMaints = fixedAssetMaints;
	}

	public List<FixedAssetMaint> getFixedAssetMaints()	{
		return fixedAssetMaints;
	}

}
