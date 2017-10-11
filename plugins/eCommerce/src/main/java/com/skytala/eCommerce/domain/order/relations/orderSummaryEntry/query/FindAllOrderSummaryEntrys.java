
package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryFound;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper.OrderSummaryEntryMapper;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;


public class FindAllOrderSummaryEntrys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderSummaryEntry> returnVal = new ArrayList<OrderSummaryEntry>();
try{
List<GenericValue> results = delegator.findAll("OrderSummaryEntry", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderSummaryEntryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderSummaryEntryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
