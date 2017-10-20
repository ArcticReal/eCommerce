package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;
public class SegmentGroupClassificationUpdated implements Event{

	private boolean success;

	public SegmentGroupClassificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
