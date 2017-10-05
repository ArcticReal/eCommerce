
package com.skytala.eCommerce.domain.communicationEventType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.communicationEventType.event.CommunicationEventTypeFound;
import com.skytala.eCommerce.domain.communicationEventType.mapper.CommunicationEventTypeMapper;
import com.skytala.eCommerce.domain.communicationEventType.model.CommunicationEventType;


public class FindAllCommunicationEventTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventType> returnVal = new ArrayList<CommunicationEventType>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
