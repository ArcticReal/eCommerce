
package com.skytala.eCommerce.domain.picklist.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.picklist.event.PicklistFound;
import com.skytala.eCommerce.domain.picklist.mapper.PicklistMapper;
import com.skytala.eCommerce.domain.picklist.model.Picklist;


public class FindAllPicklists extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Picklist> returnVal = new ArrayList<Picklist>();
try{
List<GenericValue> results = delegator.findAll("Picklist", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PicklistMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PicklistFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
