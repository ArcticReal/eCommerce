package com.skytala.eCommerce.domain.needType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.needType.model.NeedType;
public class NeedTypeFound implements Event{

	private List<NeedType> needTypes;

	public NeedTypeFound(List<NeedType> needTypes) {
		this.needTypes = needTypes;
	}

	public List<NeedType> getNeedTypes()	{
		return needTypes;
	}

}
