package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
public class AcctgTransTypeFound implements Event{

	private List<AcctgTransType> acctgTransTypes;

	public AcctgTransTypeFound(List<AcctgTransType> acctgTransTypes) {
		this.acctgTransTypes = acctgTransTypes;
	}

	public List<AcctgTransType> getAcctgTransTypes()	{
		return acctgTransTypes;
	}

}
