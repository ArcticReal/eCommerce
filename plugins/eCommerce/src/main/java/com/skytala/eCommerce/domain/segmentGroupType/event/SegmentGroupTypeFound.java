package com.skytala.eCommerce.domain.segmentGroupType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.segmentGroupType.model.SegmentGroupType;
public class SegmentGroupTypeFound implements Event{

	private List<SegmentGroupType> segmentGroupTypes;

	public SegmentGroupTypeFound(List<SegmentGroupType> segmentGroupTypes) {
		this.segmentGroupTypes = segmentGroupTypes;
	}

	public List<SegmentGroupType> getSegmentGroupTypes()	{
		return segmentGroupTypes;
	}

}
