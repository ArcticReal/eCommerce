package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;
public class GlAccountCategoryFound implements Event{

	private List<GlAccountCategory> glAccountCategorys;

	public GlAccountCategoryFound(List<GlAccountCategory> glAccountCategorys) {
		this.glAccountCategorys = glAccountCategorys;
	}

	public List<GlAccountCategory> getGlAccountCategorys()	{
		return glAccountCategorys;
	}

}
