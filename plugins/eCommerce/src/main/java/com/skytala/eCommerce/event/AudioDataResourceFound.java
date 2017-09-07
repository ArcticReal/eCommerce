package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.control.Event;

public class AudioDataResourceFound implements Event{

	private List<AudioDataResource> audioDataResources;

	public AudioDataResourceFound(List<AudioDataResource> audioDataResources) {
		this.setAudioDataResources(audioDataResources);
	}

	public List<AudioDataResource> getAudioDataResources()	{
		return audioDataResources;
	}

	public void setAudioDataResources(List<AudioDataResource> audioDataResources)	{
		this.audioDataResources = audioDataResources;
	}
}
