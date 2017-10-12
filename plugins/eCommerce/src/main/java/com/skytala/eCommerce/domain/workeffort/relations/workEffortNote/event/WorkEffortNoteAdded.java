package com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortNote.model.WorkEffortNote;
public class WorkEffortNoteAdded implements Event{

	private WorkEffortNote addedWorkEffortNote;
	private boolean success;

	public WorkEffortNoteAdded(WorkEffortNote addedWorkEffortNote, boolean success){
		this.addedWorkEffortNote = addedWorkEffortNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WorkEffortNote getAddedWorkEffortNote() {
		return addedWorkEffortNote;
	}

}
