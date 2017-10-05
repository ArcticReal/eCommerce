package com.skytala.eCommerce.domain.glFiscalType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glFiscalType.model.GlFiscalType;
public class GlFiscalTypeAdded implements Event{

	private GlFiscalType addedGlFiscalType;
	private boolean success;

	public GlFiscalTypeAdded(GlFiscalType addedGlFiscalType, boolean success){
		this.addedGlFiscalType = addedGlFiscalType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlFiscalType getAddedGlFiscalType() {
		return addedGlFiscalType;
	}

}
