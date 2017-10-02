
package com.skytala.eCommerce.domain.person.query;

import java.util.LinkedList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.person.event.PersonFound;
import com.skytala.eCommerce.domain.person.mappper.PersonMapper;
import com.skytala.eCommerce.domain.person.model.Person;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllPersons extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Person> returnVal = new LinkedList<Person>();
		try {
			List<GenericValue> results = delegator.findAll("Person", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(PersonMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new PersonFound(returnVal));
		return null;
	}
}
