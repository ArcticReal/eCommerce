
package com.skytala.eCommerce.domain.deliverableType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.deliverableType.event.DeliverableTypeFound;
import com.skytala.eCommerce.domain.deliverableType.mapper.DeliverableTypeMapper;
import com.skytala.eCommerce.domain.deliverableType.model.DeliverableType;


public class FindAllDeliverableTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DeliverableType> returnVal = new ArrayList<DeliverableType>();
try{
List<GenericValue> results = delegator.findAll("DeliverableType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DeliverableTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DeliverableTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
