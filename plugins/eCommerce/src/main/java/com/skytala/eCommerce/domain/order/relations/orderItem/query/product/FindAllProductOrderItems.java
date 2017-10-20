
package com.skytala.eCommerce.domain.order.relations.orderItem.query.product;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.product.ProductOrderItemMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.product.ProductOrderItem;


public class FindAllProductOrderItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductOrderItem> returnVal = new ArrayList<ProductOrderItem>();
try{
List<GenericValue> results = delegator.findAll("ProductOrderItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductOrderItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductOrderItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
