package com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glXbrlClass.model.GlXbrlClass;
public class GlXbrlClassFound implements Event{

	private List<GlXbrlClass> glXbrlClasss;

	public GlXbrlClassFound(List<GlXbrlClass> glXbrlClasss) {
		this.glXbrlClasss = glXbrlClasss;
	}

	public List<GlXbrlClass> getGlXbrlClasss()	{
		return glXbrlClasss;
	}

}
