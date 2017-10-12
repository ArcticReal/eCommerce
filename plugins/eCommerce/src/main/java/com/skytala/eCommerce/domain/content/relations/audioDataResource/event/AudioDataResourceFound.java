package com.skytala.eCommerce.domain.content.relations.audioDataResource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.audioDataResource.model.AudioDataResource;
public class AudioDataResourceFound implements Event{

	private List<AudioDataResource> audioDataResources;

	public AudioDataResourceFound(List<AudioDataResource> audioDataResources) {
		this.audioDataResources = audioDataResources;
	}

	public List<AudioDataResource> getAudioDataResources()	{
		return audioDataResources;
	}

}
