package com.skytala.eCommerce.domain.returnType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnType.model.ReturnType;
public class ReturnTypeFound implements Event{

	private List<ReturnType> returnTypes;

	public ReturnTypeFound(List<ReturnType> returnTypes) {
		this.returnTypes = returnTypes;
	}

	public List<ReturnType> getReturnTypes()	{
		return returnTypes;
	}

}
