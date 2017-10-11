
package com.skytala.eCommerce.domain.order.relations.orderNotification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderNotification.event.OrderNotificationFound;
import com.skytala.eCommerce.domain.order.relations.orderNotification.mapper.OrderNotificationMapper;
import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;


public class FindAllOrderNotifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderNotification> returnVal = new ArrayList<OrderNotification>();
try{
List<GenericValue> results = delegator.findAll("OrderNotification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderNotificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderNotificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
