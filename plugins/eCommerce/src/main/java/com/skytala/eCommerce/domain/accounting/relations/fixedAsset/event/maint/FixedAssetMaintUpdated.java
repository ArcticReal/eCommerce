package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maint.FixedAssetMaint;
public class FixedAssetMaintUpdated implements Event{

	private boolean success;

	public FixedAssetMaintUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
