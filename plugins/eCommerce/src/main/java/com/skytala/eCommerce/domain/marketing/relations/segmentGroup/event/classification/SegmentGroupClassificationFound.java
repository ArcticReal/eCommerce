package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;
public class SegmentGroupClassificationFound implements Event{

	private List<SegmentGroupClassification> segmentGroupClassifications;

	public SegmentGroupClassificationFound(List<SegmentGroupClassification> segmentGroupClassifications) {
		this.segmentGroupClassifications = segmentGroupClassifications;
	}

	public List<SegmentGroupClassification> getSegmentGroupClassifications()	{
		return segmentGroupClassifications;
	}

}
