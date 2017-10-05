package com.skytala.eCommerce.domain.glAccountCategoryType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountCategoryType.model.GlAccountCategoryType;
public class GlAccountCategoryTypeFound implements Event{

	private List<GlAccountCategoryType> glAccountCategoryTypes;

	public GlAccountCategoryTypeFound(List<GlAccountCategoryType> glAccountCategoryTypes) {
		this.glAccountCategoryTypes = glAccountCategoryTypes;
	}

	public List<GlAccountCategoryType> getGlAccountCategoryTypes()	{
		return glAccountCategoryTypes;
	}

}
