package com.skytala.eCommerce.domain.accommodationClass.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;
public class AccommodationClassFound implements Event{

	private List<AccommodationClass> accommodationClasss;

	public AccommodationClassFound(List<AccommodationClass> accommodationClasss) {
		this.accommodationClasss = accommodationClasss;
	}

	public List<AccommodationClass> getAccommodationClasss()	{
		return accommodationClasss;
	}

}
