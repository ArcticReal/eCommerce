package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maint.FixedAssetMaint;
public class FixedAssetMaintAdded implements Event{

	private FixedAssetMaint addedFixedAssetMaint;
	private boolean success;

	public FixedAssetMaintAdded(FixedAssetMaint addedFixedAssetMaint, boolean success){
		this.addedFixedAssetMaint = addedFixedAssetMaint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetMaint getAddedFixedAssetMaint() {
		return addedFixedAssetMaint;
	}

}
