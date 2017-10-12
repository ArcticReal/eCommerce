package com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.model.AcctgTransTypeAttr;
public class AcctgTransTypeAttrFound implements Event{

	private List<AcctgTransTypeAttr> acctgTransTypeAttrs;

	public AcctgTransTypeAttrFound(List<AcctgTransTypeAttr> acctgTransTypeAttrs) {
		this.acctgTransTypeAttrs = acctgTransTypeAttrs;
	}

	public List<AcctgTransTypeAttr> getAcctgTransTypeAttrs()	{
		return acctgTransTypeAttrs;
	}

}
