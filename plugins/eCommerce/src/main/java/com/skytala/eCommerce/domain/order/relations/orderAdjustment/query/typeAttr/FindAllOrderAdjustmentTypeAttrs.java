
package com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.typeAttr.OrderAdjustmentTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.typeAttr.OrderAdjustmentTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;


public class FindAllOrderAdjustmentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustmentTypeAttr> returnVal = new ArrayList<OrderAdjustmentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("OrderAdjustmentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAdjustmentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAdjustmentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
