
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.entity.PersonMapper;
import com.skytala.eCommerce.event.PersonFound;

public class FindAllPersons implements Query {

	@Override
	public void execute() {

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
	}
}
