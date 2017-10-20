package com.skytala.eCommerce.domain.order.relations.quote.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.role.QuoteRole;
public class QuoteRoleFound implements Event{

	private List<QuoteRole> quoteRoles;

	public QuoteRoleFound(List<QuoteRole> quoteRoles) {
		this.quoteRoles = quoteRoles;
	}

	public List<QuoteRole> getQuoteRoles()	{
		return quoteRoles;
	}

}
