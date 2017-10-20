package com.skytala.eCommerce.domain.order.relations.custRequest.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.item.CustRequestItem;
public class CustRequestItemFound implements Event{

	private List<CustRequestItem> custRequestItems;

	public CustRequestItemFound(List<CustRequestItem> custRequestItems) {
		this.custRequestItems = custRequestItems;
	}

	public List<CustRequestItem> getCustRequestItems()	{
		return custRequestItems;
	}

}
