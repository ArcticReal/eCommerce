package com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.typeAttr.DataResourceTypeAttr;
public class DataResourceTypeAttrFound implements Event{

	private List<DataResourceTypeAttr> dataResourceTypeAttrs;

	public DataResourceTypeAttrFound(List<DataResourceTypeAttr> dataResourceTypeAttrs) {
		this.dataResourceTypeAttrs = dataResourceTypeAttrs;
	}

	public List<DataResourceTypeAttr> getDataResourceTypeAttrs()	{
		return dataResourceTypeAttrs;
	}

}
