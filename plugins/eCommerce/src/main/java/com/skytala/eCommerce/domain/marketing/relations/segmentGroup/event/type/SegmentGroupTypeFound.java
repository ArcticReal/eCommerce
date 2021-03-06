package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type.SegmentGroupType;
public class SegmentGroupTypeFound implements Event{

	private List<SegmentGroupType> segmentGroupTypes;

	public SegmentGroupTypeFound(List<SegmentGroupType> segmentGroupTypes) {
		this.segmentGroupTypes = segmentGroupTypes;
	}

	public List<SegmentGroupType> getSegmentGroupTypes()	{
		return segmentGroupTypes;
	}

}
