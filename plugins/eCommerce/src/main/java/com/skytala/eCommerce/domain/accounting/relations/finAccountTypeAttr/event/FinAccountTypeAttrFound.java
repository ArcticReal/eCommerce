package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
public class FinAccountTypeAttrFound implements Event{

	private List<FinAccountTypeAttr> finAccountTypeAttrs;

	public FinAccountTypeAttrFound(List<FinAccountTypeAttr> finAccountTypeAttrs) {
		this.finAccountTypeAttrs = finAccountTypeAttrs;
	}

	public List<FinAccountTypeAttr> getFinAccountTypeAttrs()	{
		return finAccountTypeAttrs;
	}

}
