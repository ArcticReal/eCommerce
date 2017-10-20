package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryType.GlAccountCategoryType;
public class GlAccountCategoryTypeFound implements Event{

	private List<GlAccountCategoryType> glAccountCategoryTypes;

	public GlAccountCategoryTypeFound(List<GlAccountCategoryType> glAccountCategoryTypes) {
		this.glAccountCategoryTypes = glAccountCategoryTypes;
	}

	public List<GlAccountCategoryType> getGlAccountCategoryTypes()	{
		return glAccountCategoryTypes;
	}

}
