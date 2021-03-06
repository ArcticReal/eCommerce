package com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;
public class PerfRatingTypeFound implements Event{

	private List<PerfRatingType> perfRatingTypes;

	public PerfRatingTypeFound(List<PerfRatingType> perfRatingTypes) {
		this.perfRatingTypes = perfRatingTypes;
	}

	public List<PerfRatingType> getPerfRatingTypes()	{
		return perfRatingTypes;
	}

}
