package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;
public class FinAccountTransFound implements Event{

	private List<FinAccountTrans> finAccountTranss;

	public FinAccountTransFound(List<FinAccountTrans> finAccountTranss) {
		this.finAccountTranss = finAccountTranss;
	}

	public List<FinAccountTrans> getFinAccountTranss()	{
		return finAccountTranss;
	}

}
