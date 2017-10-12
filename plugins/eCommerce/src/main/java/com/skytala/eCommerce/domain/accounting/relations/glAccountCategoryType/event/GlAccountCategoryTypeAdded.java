package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryType.model.GlAccountCategoryType;
public class GlAccountCategoryTypeAdded implements Event{

	private GlAccountCategoryType addedGlAccountCategoryType;
	private boolean success;

	public GlAccountCategoryTypeAdded(GlAccountCategoryType addedGlAccountCategoryType, boolean success){
		this.addedGlAccountCategoryType = addedGlAccountCategoryType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountCategoryType getAddedGlAccountCategoryType() {
		return addedGlAccountCategoryType;
	}

}
