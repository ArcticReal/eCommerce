package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model.WorkEffortNote;
public class WorkEffortNoteDeleted implements Event{

	private boolean success;

	public WorkEffortNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
