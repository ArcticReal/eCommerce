package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.TrackingCode;
public class TrackingCodeFound implements Event{

	private List<TrackingCode> trackingCodes;

	public TrackingCodeFound(List<TrackingCode> trackingCodes) {
		this.trackingCodes = trackingCodes;
	}

	public List<TrackingCode> getTrackingCodes()	{
		return trackingCodes;
	}

}
