package com.skytala.eCommerce.domain.humanres.relations.performanceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
public class PerformanceNoteAdded implements Event{

	private PerformanceNote addedPerformanceNote;
	private boolean success;

	public PerformanceNoteAdded(PerformanceNote addedPerformanceNote, boolean success){
		this.addedPerformanceNote = addedPerformanceNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PerformanceNote getAddedPerformanceNote() {
		return addedPerformanceNote;
	}

}
