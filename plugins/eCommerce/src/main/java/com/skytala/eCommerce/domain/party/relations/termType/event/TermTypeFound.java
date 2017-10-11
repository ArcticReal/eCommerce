package com.skytala.eCommerce.domain.party.relations.termType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;
public class TermTypeFound implements Event{

	private List<TermType> termTypes;

	public TermTypeFound(List<TermType> termTypes) {
		this.termTypes = termTypes;
	}

	public List<TermType> getTermTypes()	{
		return termTypes;
	}

}
