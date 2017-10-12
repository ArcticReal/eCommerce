package com.skytala.eCommerce.domain.humanres.relations.payHistory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
public class PayHistoryFound implements Event{

	private List<PayHistory> payHistorys;

	public PayHistoryFound(List<PayHistory> payHistorys) {
		this.payHistorys = payHistorys;
	}

	public List<PayHistory> getPayHistorys()	{
		return payHistorys;
	}

}
