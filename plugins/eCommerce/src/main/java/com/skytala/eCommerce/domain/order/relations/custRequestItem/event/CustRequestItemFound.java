package com.skytala.eCommerce.domain.order.relations.custRequestItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItem.model.CustRequestItem;
public class CustRequestItemFound implements Event{

	private List<CustRequestItem> custRequestItems;

	public CustRequestItemFound(List<CustRequestItem> custRequestItems) {
		this.custRequestItems = custRequestItems;
	}

	public List<CustRequestItem> getCustRequestItems()	{
		return custRequestItems;
	}

}
