package com.skytala.eCommerce.domain.quantityBreak.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreak.model.QuantityBreak;
public class QuantityBreakFound implements Event{

	private List<QuantityBreak> quantityBreaks;

	public QuantityBreakFound(List<QuantityBreak> quantityBreaks) {
		this.quantityBreaks = quantityBreaks;
	}

	public List<QuantityBreak> getQuantityBreaks()	{
		return quantityBreaks;
	}

}
