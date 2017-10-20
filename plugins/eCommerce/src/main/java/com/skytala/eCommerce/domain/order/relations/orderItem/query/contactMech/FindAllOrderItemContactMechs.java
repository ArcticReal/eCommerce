
package com.skytala.eCommerce.domain.order.relations.orderItem.query.contactMech;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech.OrderItemContactMechFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.contactMech.OrderItemContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;


public class FindAllOrderItemContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemContactMech> returnVal = new ArrayList<OrderItemContactMech>();
try{
List<GenericValue> results = delegator.findAll("OrderItemContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
