package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.model.FixedAssetMaint;
public class FixedAssetMaintDeleted implements Event{

	private boolean success;

	public FixedAssetMaintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
