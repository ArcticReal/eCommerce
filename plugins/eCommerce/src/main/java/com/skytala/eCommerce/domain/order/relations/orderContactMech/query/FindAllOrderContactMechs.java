
package com.skytala.eCommerce.domain.order.relations.orderContactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.event.OrderContactMechFound;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.mapper.OrderContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;


public class FindAllOrderContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderContactMech> returnVal = new ArrayList<OrderContactMech>();
try{
List<GenericValue> results = delegator.findAll("OrderContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
