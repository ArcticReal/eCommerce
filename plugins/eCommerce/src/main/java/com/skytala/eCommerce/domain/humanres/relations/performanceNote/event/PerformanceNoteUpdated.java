package com.skytala.eCommerce.domain.humanres.relations.performanceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
public class PerformanceNoteUpdated implements Event{

	private boolean success;

	public PerformanceNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
