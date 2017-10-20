package com.skytala.eCommerce.domain.content.relations.dataResource.event.audio;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;
public class AudioDataResourceFound implements Event{

	private List<AudioDataResource> audioDataResources;

	public AudioDataResourceFound(List<AudioDataResource> audioDataResources) {
		this.audioDataResources = audioDataResources;
	}

	public List<AudioDataResource> getAudioDataResources()	{
		return audioDataResources;
	}

}
