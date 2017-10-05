package com.skytala.eCommerce.domain.characterSet.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;
public class CharacterSetAdded implements Event{

	private CharacterSet addedCharacterSet;
	private boolean success;

	public CharacterSetAdded(CharacterSet addedCharacterSet, boolean success){
		this.addedCharacterSet = addedCharacterSet;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CharacterSet getAddedCharacterSet() {
		return addedCharacterSet;
	}

}
