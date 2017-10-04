
package com.skytala.eCommerce.domain.person.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.person.event.PersonFound;
import com.skytala.eCommerce.domain.person.mapper.PersonMapper;
import com.skytala.eCommerce.domain.person.model.Person;


public class FindAllPersons extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Person> returnVal = new ArrayList<Person>();
try{
List<GenericValue> results = delegator.findAll("Person", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PersonMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PersonFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
