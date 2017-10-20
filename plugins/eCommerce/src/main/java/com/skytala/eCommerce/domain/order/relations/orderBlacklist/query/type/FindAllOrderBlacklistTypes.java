
package com.skytala.eCommerce.domain.order.relations.orderBlacklist.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type.OrderBlacklistTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.type.OrderBlacklistTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;


public class FindAllOrderBlacklistTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderBlacklistType> returnVal = new ArrayList<OrderBlacklistType>();
try{
List<GenericValue> results = delegator.findAll("OrderBlacklistType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderBlacklistTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderBlacklistTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
