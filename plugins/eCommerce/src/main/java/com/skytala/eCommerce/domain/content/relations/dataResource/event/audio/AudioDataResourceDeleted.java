package com.skytala.eCommerce.domain.content.relations.dataResource.event.audio;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;
public class AudioDataResourceDeleted implements Event{

	private boolean success;

	public AudioDataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
