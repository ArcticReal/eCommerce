package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Party;

public class PartyFound implements Event{

	private List<Party> partys;

	public PartyFound(List<Party> partys) {
		this.setPartys(partys);
	}

	public List<Party> getPartys()	{
		return partys;
	}

	public void setPartys(List<Party> partys)	{
		this.partys = partys;
	}
}
