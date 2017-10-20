package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;
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
