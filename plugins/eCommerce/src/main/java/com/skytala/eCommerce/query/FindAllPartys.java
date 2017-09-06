package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;
import com.skytala.eCommerce.event.PartyFound;

public class FindAllPartys implements Query {

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Party> returnVal = new LinkedList<Party>();
		try {
			List<GenericValue> results = delegator.findAll("Party", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(PartyMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new PartyFound(returnVal));
	}
}
