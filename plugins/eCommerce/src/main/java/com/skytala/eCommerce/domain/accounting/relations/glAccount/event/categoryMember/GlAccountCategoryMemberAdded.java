package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;
public class GlAccountCategoryMemberAdded implements Event{

	private GlAccountCategoryMember addedGlAccountCategoryMember;
	private boolean success;

	public GlAccountCategoryMemberAdded(GlAccountCategoryMember addedGlAccountCategoryMember, boolean success){
		this.addedGlAccountCategoryMember = addedGlAccountCategoryMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountCategoryMember getAddedGlAccountCategoryMember() {
		return addedGlAccountCategoryMember;
	}

}
