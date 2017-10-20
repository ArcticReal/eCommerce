package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr.AcctgTransTypeAttr;
public class AcctgTransTypeAttrFound implements Event{

	private List<AcctgTransTypeAttr> acctgTransTypeAttrs;

	public AcctgTransTypeAttrFound(List<AcctgTransTypeAttr> acctgTransTypeAttrs) {
		this.acctgTransTypeAttrs = acctgTransTypeAttrs;
	}

	public List<AcctgTransTypeAttr> getAcctgTransTypeAttrs()	{
		return acctgTransTypeAttrs;
	}

}
