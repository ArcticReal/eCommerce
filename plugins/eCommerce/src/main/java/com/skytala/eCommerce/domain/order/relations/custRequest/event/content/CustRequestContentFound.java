package com.skytala.eCommerce.domain.order.relations.custRequest.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;
public class CustRequestContentFound implements Event{

	private List<CustRequestContent> custRequestContents;

	public CustRequestContentFound(List<CustRequestContent> custRequestContents) {
		this.custRequestContents = custRequestContents;
	}

	public List<CustRequestContent> getCustRequestContents()	{
		return custRequestContents;
	}

}
