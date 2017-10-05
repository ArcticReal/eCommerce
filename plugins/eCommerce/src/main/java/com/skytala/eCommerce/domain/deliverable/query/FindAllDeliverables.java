
package com.skytala.eCommerce.domain.deliverable.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.deliverable.event.DeliverableFound;
import com.skytala.eCommerce.domain.deliverable.mapper.DeliverableMapper;
import com.skytala.eCommerce.domain.deliverable.model.Deliverable;


public class FindAllDeliverables extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Deliverable> returnVal = new ArrayList<Deliverable>();
try{
List<GenericValue> results = delegator.findAll("Deliverable", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DeliverableMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DeliverableFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
