
package com.skytala.eCommerce.domain.order.relations.orderItem.query.change;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.change.OrderItemChangeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.change.OrderItemChangeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.change.OrderItemChange;


public class FindAllOrderItemChanges extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemChange> returnVal = new ArrayList<OrderItemChange>();
try{
List<GenericValue> results = delegator.findAll("OrderItemChange", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemChangeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemChangeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
