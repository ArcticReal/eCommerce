
package com.skytala.eCommerce.domain.subscription.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.subscription.event.SubscriptionFound;
import com.skytala.eCommerce.domain.subscription.mapper.SubscriptionMapper;
import com.skytala.eCommerce.domain.subscription.model.Subscription;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllSubscriptions extends Query {

	@Override
	public Event execute() {

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
		return null;
	}
}
