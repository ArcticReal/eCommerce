package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;
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
