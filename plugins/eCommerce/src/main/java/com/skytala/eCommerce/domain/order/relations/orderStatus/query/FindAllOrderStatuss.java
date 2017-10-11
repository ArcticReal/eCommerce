
package com.skytala.eCommerce.domain.order.relations.orderStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderStatus.event.OrderStatusFound;
import com.skytala.eCommerce.domain.order.relations.orderStatus.mapper.OrderStatusMapper;
import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;


public class FindAllOrderStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderStatus> returnVal = new ArrayList<OrderStatus>();
try{
List<GenericValue> results = delegator.findAll("OrderStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
