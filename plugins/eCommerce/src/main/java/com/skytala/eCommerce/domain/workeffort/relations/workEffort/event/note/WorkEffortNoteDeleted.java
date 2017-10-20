package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;
public class WorkEffortNoteDeleted implements Event{

	private boolean success;

	public WorkEffortNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
