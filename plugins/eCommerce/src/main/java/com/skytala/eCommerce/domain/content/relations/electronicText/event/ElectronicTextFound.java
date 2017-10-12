package com.skytala.eCommerce.domain.content.relations.electronicText.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
public class ElectronicTextFound implements Event{

	private List<ElectronicText> electronicTexts;

	public ElectronicTextFound(List<ElectronicText> electronicTexts) {
		this.electronicTexts = electronicTexts;
	}

	public List<ElectronicText> getElectronicTexts()	{
		return electronicTexts;
	}

}
