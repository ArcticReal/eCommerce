package com.skytala.eCommerce.domain.segmentGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.segmentGroup.model.SegmentGroup;
public class SegmentGroupFound implements Event{

	private List<SegmentGroup> segmentGroups;

	public SegmentGroupFound(List<SegmentGroup> segmentGroups) {
		this.segmentGroups = segmentGroups;
	}

	public List<SegmentGroup> getSegmentGroups()	{
		return segmentGroups;
	}

}
