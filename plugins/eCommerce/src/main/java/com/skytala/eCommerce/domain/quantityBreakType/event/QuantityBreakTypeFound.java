package com.skytala.eCommerce.domain.quantityBreakType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreakType.model.QuantityBreakType;
public class QuantityBreakTypeFound implements Event{

	private List<QuantityBreakType> quantityBreakTypes;

	public QuantityBreakTypeFound(List<QuantityBreakType> quantityBreakTypes) {
		this.quantityBreakTypes = quantityBreakTypes;
	}

	public List<QuantityBreakType> getQuantityBreakTypes()	{
		return quantityBreakTypes;
	}

}
