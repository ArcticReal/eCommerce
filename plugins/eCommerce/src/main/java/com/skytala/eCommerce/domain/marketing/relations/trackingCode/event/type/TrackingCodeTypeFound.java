package com.skytala.eCommerce.domain.marketing.relations.trackingCode.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.trackingCode.model.type.TrackingCodeType;
public class TrackingCodeTypeFound implements Event{

	private List<TrackingCodeType> trackingCodeTypes;

	public TrackingCodeTypeFound(List<TrackingCodeType> trackingCodeTypes) {
		this.trackingCodeTypes = trackingCodeTypes;
	}

	public List<TrackingCodeType> getTrackingCodeTypes()	{
		return trackingCodeTypes;
	}

}
