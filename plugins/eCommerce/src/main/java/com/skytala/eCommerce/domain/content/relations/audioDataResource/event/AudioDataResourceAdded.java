package com.skytala.eCommerce.domain.content.relations.audioDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.audioDataResource.model.AudioDataResource;
public class AudioDataResourceAdded implements Event{

	private AudioDataResource addedAudioDataResource;
	private boolean success;

	public AudioDataResourceAdded(AudioDataResource addedAudioDataResource, boolean success){
		this.addedAudioDataResource = addedAudioDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AudioDataResource getAddedAudioDataResource() {
		return addedAudioDataResource;
	}

}
