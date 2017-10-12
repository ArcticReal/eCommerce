package com.skytala.eCommerce.domain.accounting.relations.glAccountCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountCategory.model.GlAccountCategory;
public class GlAccountCategoryAdded implements Event{

	private GlAccountCategory addedGlAccountCategory;
	private boolean success;

	public GlAccountCategoryAdded(GlAccountCategory addedGlAccountCategory, boolean success){
		this.addedGlAccountCategory = addedGlAccountCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountCategory getAddedGlAccountCategory() {
		return addedGlAccountCategory;
	}

}
