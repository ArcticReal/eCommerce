package com.skytala.eCommerce.domain.order.relations.custRequestAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestAttribute.model.CustRequestAttribute;
public class CustRequestAttributeFound implements Event{

	private List<CustRequestAttribute> custRequestAttributes;

	public CustRequestAttributeFound(List<CustRequestAttribute> custRequestAttributes) {
		this.custRequestAttributes = custRequestAttributes;
	}

	public List<CustRequestAttribute> getCustRequestAttributes()	{
		return custRequestAttributes;
	}

}
