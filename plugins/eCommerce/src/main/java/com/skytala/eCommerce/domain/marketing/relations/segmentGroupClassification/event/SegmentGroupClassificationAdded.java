package com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.model.SegmentGroupClassification;
public class SegmentGroupClassificationAdded implements Event{

	private SegmentGroupClassification addedSegmentGroupClassification;
	private boolean success;

	public SegmentGroupClassificationAdded(SegmentGroupClassification addedSegmentGroupClassification, boolean success){
		this.addedSegmentGroupClassification = addedSegmentGroupClassification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SegmentGroupClassification getAddedSegmentGroupClassification() {
		return addedSegmentGroupClassification;
	}

}
