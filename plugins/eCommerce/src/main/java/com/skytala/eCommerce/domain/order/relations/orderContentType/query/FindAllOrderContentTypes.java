
package com.skytala.eCommerce.domain.order.relations.orderContentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderContentType.event.OrderContentTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderContentType.mapper.OrderContentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderContentType.model.OrderContentType;


public class FindAllOrderContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderContentType> returnVal = new ArrayList<OrderContentType>();
try{
List<GenericValue> results = delegator.findAll("OrderContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
