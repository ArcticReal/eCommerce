package com.skytala.eCommerce.domain.glFiscalType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glFiscalType.model.GlFiscalType;
public class GlFiscalTypeFound implements Event{

	private List<GlFiscalType> glFiscalTypes;

	public GlFiscalTypeFound(List<GlFiscalType> glFiscalTypes) {
		this.glFiscalTypes = glFiscalTypes;
	}

	public List<GlFiscalType> getGlFiscalTypes()	{
		return glFiscalTypes;
	}

}
