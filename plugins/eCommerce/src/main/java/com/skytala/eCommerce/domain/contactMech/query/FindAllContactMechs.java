
package com.skytala.eCommerce.domain.contactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.contactMech.event.ContactMechFound;
import com.skytala.eCommerce.domain.contactMech.mapper.ContactMechMapper;
import com.skytala.eCommerce.domain.contactMech.model.ContactMech;


public class FindAllContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMech> returnVal = new ArrayList<ContactMech>();
try{
List<GenericValue> results = delegator.findAll("ContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
