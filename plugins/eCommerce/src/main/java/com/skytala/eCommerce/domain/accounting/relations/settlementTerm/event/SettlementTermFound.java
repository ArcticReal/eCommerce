package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;
public class SettlementTermFound implements Event{

	private List<SettlementTerm> settlementTerms;

	public SettlementTermFound(List<SettlementTerm> settlementTerms) {
		this.settlementTerms = settlementTerms;
	}

	public List<SettlementTerm> getSettlementTerms()	{
		return settlementTerms;
	}

}
