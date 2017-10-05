package com.skytala.eCommerce.domain.glAccountClass.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountClass.model.GlAccountClass;
public class GlAccountClassFound implements Event{

	private List<GlAccountClass> glAccountClasss;

	public GlAccountClassFound(List<GlAccountClass> glAccountClasss) {
		this.glAccountClasss = glAccountClasss;
	}

	public List<GlAccountClass> getGlAccountClasss()	{
		return glAccountClasss;
	}

}
