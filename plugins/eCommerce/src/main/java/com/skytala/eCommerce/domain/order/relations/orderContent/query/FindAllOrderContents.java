
package com.skytala.eCommerce.domain.order.relations.orderContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.OrderContentFound;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.OrderContentMapper;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;


public class FindAllOrderContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderContent> returnVal = new ArrayList<OrderContent>();
try{
List<GenericValue> results = delegator.findAll("OrderContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
