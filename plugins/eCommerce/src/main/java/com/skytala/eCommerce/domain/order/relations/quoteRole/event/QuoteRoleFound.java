package com.skytala.eCommerce.domain.order.relations.quoteRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteRole.model.QuoteRole;
public class QuoteRoleFound implements Event{

	private List<QuoteRole> quoteRoles;

	public QuoteRoleFound(List<QuoteRole> quoteRoles) {
		this.quoteRoles = quoteRoles;
	}

	public List<QuoteRole> getQuoteRoles()	{
		return quoteRoles;
	}

}
