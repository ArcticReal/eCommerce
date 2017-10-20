package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeAttr.FinAccountTypeAttr;
public class FinAccountTypeAttrFound implements Event{

	private List<FinAccountTypeAttr> finAccountTypeAttrs;

	public FinAccountTypeAttrFound(List<FinAccountTypeAttr> finAccountTypeAttrs) {
		this.finAccountTypeAttrs = finAccountTypeAttrs;
	}

	public List<FinAccountTypeAttr> getFinAccountTypeAttrs()	{
		return finAccountTypeAttrs;
	}

}
