package com.skytala.eCommerce.domain.content.relations.electronicText.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
public class ElectronicTextAdded implements Event{

	private ElectronicText addedElectronicText;
	private boolean success;

	public ElectronicTextAdded(ElectronicText addedElectronicText, boolean success){
		this.addedElectronicText = addedElectronicText;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ElectronicText getAddedElectronicText() {
		return addedElectronicText;
	}

}
