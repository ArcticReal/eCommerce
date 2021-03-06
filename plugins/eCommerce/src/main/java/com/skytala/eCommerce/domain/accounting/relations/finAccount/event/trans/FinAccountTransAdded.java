package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;
public class FinAccountTransAdded implements Event{

	private FinAccountTrans addedFinAccountTrans;
	private boolean success;

	public FinAccountTransAdded(FinAccountTrans addedFinAccountTrans, boolean success){
		this.addedFinAccountTrans = addedFinAccountTrans;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTrans getAddedFinAccountTrans() {
		return addedFinAccountTrans;
	}

}
