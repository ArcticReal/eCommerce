
package com.skytala.eCommerce.domain.product.relations.productGroupOrder.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productGroupOrder.event.ProductGroupOrderFound;
import com.skytala.eCommerce.domain.product.relations.productGroupOrder.mapper.ProductGroupOrderMapper;
import com.skytala.eCommerce.domain.product.relations.productGroupOrder.model.ProductGroupOrder;


public class FindAllProductGroupOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductGroupOrder> returnVal = new ArrayList<ProductGroupOrder>();
try{
List<GenericValue> results = delegator.findAll("ProductGroupOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductGroupOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductGroupOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
