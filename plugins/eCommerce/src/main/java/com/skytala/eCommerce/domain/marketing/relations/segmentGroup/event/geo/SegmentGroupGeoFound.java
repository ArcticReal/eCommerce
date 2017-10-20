package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.geo;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.geo.SegmentGroupGeo;
public class SegmentGroupGeoFound implements Event{

	private List<SegmentGroupGeo> segmentGroupGeos;

	public SegmentGroupGeoFound(List<SegmentGroupGeo> segmentGroupGeos) {
		this.segmentGroupGeos = segmentGroupGeos;
	}

	public List<SegmentGroupGeo> getSegmentGroupGeos()	{
		return segmentGroupGeos;
	}

}
