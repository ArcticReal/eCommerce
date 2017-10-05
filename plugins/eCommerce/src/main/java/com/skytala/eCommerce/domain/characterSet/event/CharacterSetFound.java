package com.skytala.eCommerce.domain.characterSet.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
public class CharacterSetFound implements Event{

	private List<CharacterSet> characterSets;

	public CharacterSetFound(List<CharacterSet> characterSets) {
		this.characterSets = characterSets;
	}

	public List<CharacterSet> getCharacterSets()	{
		return characterSets;
	}

}
