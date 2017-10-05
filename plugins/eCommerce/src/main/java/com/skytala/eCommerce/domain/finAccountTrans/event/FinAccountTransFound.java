package com.skytala.eCommerce.domain.finAccountTrans.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountTrans.model.FinAccountTrans;
public class FinAccountTransFound implements Event{

	private List<FinAccountTrans> finAccountTranss;

	public FinAccountTransFound(List<FinAccountTrans> finAccountTranss) {
		this.finAccountTranss = finAccountTranss;
	}

	public List<FinAccountTrans> getFinAccountTranss()	{
		return finAccountTranss;
	}

}
