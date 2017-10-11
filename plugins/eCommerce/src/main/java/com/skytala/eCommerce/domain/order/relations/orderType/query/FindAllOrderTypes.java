
package com.skytala.eCommerce.domain.order.relations.orderType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.OrderTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;


public class FindAllOrderTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderType> returnVal = new ArrayList<OrderType>();
try{
List<GenericValue> results = delegator.findAll("OrderType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
