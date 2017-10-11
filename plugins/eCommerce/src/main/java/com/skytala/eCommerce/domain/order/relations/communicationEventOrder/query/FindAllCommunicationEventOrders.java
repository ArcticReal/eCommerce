
package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderFound;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.mapper.CommunicationEventOrderMapper;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;


public class FindAllCommunicationEventOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventOrder> returnVal = new ArrayList<CommunicationEventOrder>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
