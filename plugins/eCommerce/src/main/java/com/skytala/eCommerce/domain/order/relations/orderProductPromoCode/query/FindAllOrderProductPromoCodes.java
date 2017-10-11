
package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.event.OrderProductPromoCodeFound;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.mapper.OrderProductPromoCodeMapper;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;


public class FindAllOrderProductPromoCodes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderProductPromoCode> returnVal = new ArrayList<OrderProductPromoCode>();
try{
List<GenericValue> results = delegator.findAll("OrderProductPromoCode", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderProductPromoCodeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderProductPromoCodeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
