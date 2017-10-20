package com.skytala.eCommerce.domain.product.relations.costComponent.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.typeAttr.CostComponentTypeAttr;
public class CostComponentTypeAttrFound implements Event{

	private List<CostComponentTypeAttr> costComponentTypeAttrs;

	public CostComponentTypeAttrFound(List<CostComponentTypeAttr> costComponentTypeAttrs) {
		this.costComponentTypeAttrs = costComponentTypeAttrs;
	}

	public List<CostComponentTypeAttr> getCostComponentTypeAttrs()	{
		return costComponentTypeAttrs;
	}

}
