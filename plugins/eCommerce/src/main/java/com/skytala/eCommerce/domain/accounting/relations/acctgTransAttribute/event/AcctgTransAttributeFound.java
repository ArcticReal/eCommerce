package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;
public class AcctgTransAttributeFound implements Event{

	private List<AcctgTransAttribute> acctgTransAttributes;

	public AcctgTransAttributeFound(List<AcctgTransAttribute> acctgTransAttributes) {
		this.acctgTransAttributes = acctgTransAttributes;
	}

	public List<AcctgTransAttribute> getAcctgTransAttributes()	{
		return acctgTransAttributes;
	}

}
