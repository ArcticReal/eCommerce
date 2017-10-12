package com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCodeVisit.model.TrackingCodeVisit;
public class TrackingCodeVisitFound implements Event{

	private List<TrackingCodeVisit> trackingCodeVisits;

	public TrackingCodeVisitFound(List<TrackingCodeVisit> trackingCodeVisits) {
		this.trackingCodeVisits = trackingCodeVisits;
	}

	public List<TrackingCodeVisit> getTrackingCodeVisits()	{
		return trackingCodeVisits;
	}

}
