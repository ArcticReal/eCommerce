
package com.skytala.eCommerce.domain.communicationEvent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.communicationEvent.event.CommunicationEventFound;
import com.skytala.eCommerce.domain.communicationEvent.mapper.CommunicationEventMapper;
import com.skytala.eCommerce.domain.communicationEvent.model.CommunicationEvent;


public class FindAllCommunicationEvents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEvent> returnVal = new ArrayList<CommunicationEvent>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEvent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
