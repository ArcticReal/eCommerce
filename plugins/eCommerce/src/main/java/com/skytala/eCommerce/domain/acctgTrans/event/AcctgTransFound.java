package com.skytala.eCommerce.domain.acctgTrans.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTrans.model.AcctgTrans;
public class AcctgTransFound implements Event{

	private List<AcctgTrans> acctgTranss;

	public AcctgTransFound(List<AcctgTrans> acctgTranss) {
		this.acctgTranss = acctgTranss;
	}

	public List<AcctgTrans> getAcctgTranss()	{
		return acctgTranss;
	}

}
