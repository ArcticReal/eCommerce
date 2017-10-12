package com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.model.GlFiscalType;
public class GlFiscalTypeDeleted implements Event{

	private boolean success;

	public GlFiscalTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
