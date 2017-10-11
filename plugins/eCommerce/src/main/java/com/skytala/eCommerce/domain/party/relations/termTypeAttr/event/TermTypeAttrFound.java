package com.skytala.eCommerce.domain.party.relations.termTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.termTypeAttr.model.TermTypeAttr;
public class TermTypeAttrFound implements Event{

	private List<TermTypeAttr> termTypeAttrs;

	public TermTypeAttrFound(List<TermTypeAttr> termTypeAttrs) {
		this.termTypeAttrs = termTypeAttrs;
	}

	public List<TermTypeAttr> getTermTypeAttrs()	{
		return termTypeAttrs;
	}

}
