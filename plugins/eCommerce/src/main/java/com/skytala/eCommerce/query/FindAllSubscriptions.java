
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Subscription;
import com.skytala.eCommerce.entity.SubscriptionMapper;
import com.skytala.eCommerce.event.SubscriptionFound;

public class FindAllSubscriptions implements Query {

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Subscription> returnVal = new LinkedList<Subscription>();
		try {
			List<GenericValue> results = delegator.findAll("Subscription", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(SubscriptionMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new SubscriptionFound(returnVal));
	}
}
