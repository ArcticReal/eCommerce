
package com.skytala.eCommerce.domain.order.relations.orderItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.OrderItemFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.OrderItemMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;


public class FindAllOrderItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItem> returnVal = new ArrayList<OrderItem>();
try{
List<GenericValue> results = delegator.findAll("OrderItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
