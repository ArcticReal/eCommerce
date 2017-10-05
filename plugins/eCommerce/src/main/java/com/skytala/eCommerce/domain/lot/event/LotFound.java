package com.skytala.eCommerce.domain.lot.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.lot.model.Lot;
public class LotFound implements Event{

	private List<Lot> lots;

	public LotFound(List<Lot> lots) {
		this.lots = lots;
	}

	public List<Lot> getLots()	{
		return lots;
	}

}
