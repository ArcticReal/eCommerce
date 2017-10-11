
package com.skytala.eCommerce.domain.order.relations.orderHeader.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;


public class FindAllOrderHeaders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderHeader> returnVal = new ArrayList<OrderHeader>();
try{
List<GenericValue> results = delegator.findAll("OrderHeader", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderHeaderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderHeaderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
