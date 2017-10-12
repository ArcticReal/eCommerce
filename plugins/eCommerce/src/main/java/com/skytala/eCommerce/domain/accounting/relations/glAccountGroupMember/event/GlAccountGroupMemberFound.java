package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
public class GlAccountGroupMemberFound implements Event{

	private List<GlAccountGroupMember> glAccountGroupMembers;

	public GlAccountGroupMemberFound(List<GlAccountGroupMember> glAccountGroupMembers) {
		this.glAccountGroupMembers = glAccountGroupMembers;
	}

	public List<GlAccountGroupMember> getGlAccountGroupMembers()	{
		return glAccountGroupMembers;
	}

}
