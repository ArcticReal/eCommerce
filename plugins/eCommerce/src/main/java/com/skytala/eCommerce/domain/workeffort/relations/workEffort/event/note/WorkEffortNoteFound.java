package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;
public class WorkEffortNoteFound implements Event{

	private List<WorkEffortNote> workEffortNotes;

	public WorkEffortNoteFound(List<WorkEffortNote> workEffortNotes) {
		this.workEffortNotes = workEffortNotes;
	}

	public List<WorkEffortNote> getWorkEffortNotes()	{
		return workEffortNotes;
	}

}
