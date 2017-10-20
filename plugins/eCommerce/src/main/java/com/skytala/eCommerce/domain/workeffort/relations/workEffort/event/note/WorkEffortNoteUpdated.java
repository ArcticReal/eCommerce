package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;
public class WorkEffortNoteUpdated implements Event{

	private boolean success;

	public WorkEffortNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
