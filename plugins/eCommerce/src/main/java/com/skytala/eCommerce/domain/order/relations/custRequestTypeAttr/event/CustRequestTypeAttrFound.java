package com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.model.CustRequestTypeAttr;
public class CustRequestTypeAttrFound implements Event{

	private List<CustRequestTypeAttr> custRequestTypeAttrs;

	public CustRequestTypeAttrFound(List<CustRequestTypeAttr> custRequestTypeAttrs) {
		this.custRequestTypeAttrs = custRequestTypeAttrs;
	}

	public List<CustRequestTypeAttr> getCustRequestTypeAttrs()	{
		return custRequestTypeAttrs;
	}

}
