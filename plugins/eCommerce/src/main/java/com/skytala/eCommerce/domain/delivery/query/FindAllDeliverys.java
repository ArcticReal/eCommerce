
package com.skytala.eCommerce.domain.delivery.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.delivery.event.DeliveryFound;
import com.skytala.eCommerce.domain.delivery.mapper.DeliveryMapper;
import com.skytala.eCommerce.domain.delivery.model.Delivery;


public class FindAllDeliverys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Delivery> returnVal = new ArrayList<Delivery>();
try{
List<GenericValue> results = delegator.findAll("Delivery", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DeliveryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DeliveryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
