package com.skytala.eCommerce.domain.content.relations.audioDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.audioDataResource.model.AudioDataResource;
public class AudioDataResourceUpdated implements Event{

	private boolean success;

	public AudioDataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
