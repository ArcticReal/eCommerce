package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.model.GlAccountCategoryMember;
public class GlAccountCategoryMemberFound implements Event{

	private List<GlAccountCategoryMember> glAccountCategoryMembers;

	public GlAccountCategoryMemberFound(List<GlAccountCategoryMember> glAccountCategoryMembers) {
		this.glAccountCategoryMembers = glAccountCategoryMembers;
	}

	public List<GlAccountCategoryMember> getGlAccountCategoryMembers()	{
		return glAccountCategoryMembers;
	}

}
