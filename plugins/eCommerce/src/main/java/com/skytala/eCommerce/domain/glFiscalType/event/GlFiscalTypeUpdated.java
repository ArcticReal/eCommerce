package com.skytala.eCommerce.domain.glFiscalType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glFiscalType.model.GlFiscalType;
public class GlFiscalTypeUpdated implements Event{

	private boolean success;

	public GlFiscalTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
