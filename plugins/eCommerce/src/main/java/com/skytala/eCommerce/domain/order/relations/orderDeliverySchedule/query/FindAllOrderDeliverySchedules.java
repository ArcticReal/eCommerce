
package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.event.OrderDeliveryScheduleFound;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.mapper.OrderDeliveryScheduleMapper;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model.OrderDeliverySchedule;


public class FindAllOrderDeliverySchedules extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderDeliverySchedule> returnVal = new ArrayList<OrderDeliverySchedule>();
try{
List<GenericValue> results = delegator.findAll("OrderDeliverySchedule", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderDeliveryScheduleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderDeliveryScheduleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
