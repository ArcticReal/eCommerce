package com.skytala.eCommerce.domain.humanres.relations.performanceNote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
public class PerformanceNoteFound implements Event{

	private List<PerformanceNote> performanceNotes;

	public PerformanceNoteFound(List<PerformanceNote> performanceNotes) {
		this.performanceNotes = performanceNotes;
	}

	public List<PerformanceNote> getPerformanceNotes()	{
		return performanceNotes;
	}

}
