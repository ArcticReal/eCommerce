package com.skytala.eCommerce.domain.humanres.relations.performanceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
public class PerformanceNoteDeleted implements Event{

	private boolean success;

	public PerformanceNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
