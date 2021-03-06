
package com.skytala.eCommerce.domain.order.relations.orderItem.query.priceInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.priceInfo.OrderItemPriceInfoFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.priceInfo.OrderItemPriceInfoMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.priceInfo.OrderItemPriceInfo;


public class FindAllOrderItemPriceInfos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemPriceInfo> returnVal = new ArrayList<OrderItemPriceInfo>();
try{
List<GenericValue> results = delegator.findAll("OrderItemPriceInfo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemPriceInfoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemPriceInfoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
