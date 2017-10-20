package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;
public class FinAccountTransTypeAttrFound implements Event{

	private List<FinAccountTransTypeAttr> finAccountTransTypeAttrs;

	public FinAccountTransTypeAttrFound(List<FinAccountTransTypeAttr> finAccountTransTypeAttrs) {
		this.finAccountTransTypeAttrs = finAccountTransTypeAttrs;
	}

	public List<FinAccountTransTypeAttr> getFinAccountTransTypeAttrs()	{
		return finAccountTransTypeAttrs;
	}

}
