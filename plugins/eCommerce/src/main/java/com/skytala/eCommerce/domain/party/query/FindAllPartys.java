package com.skytala.eCommerce.domain.party.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.party.event.PartyFound;
import com.skytala.eCommerce.domain.party.mapper.PartyMapper;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllPartys extends Query {

	@Override
	public Event execute() {

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
		return null;
	}
}
