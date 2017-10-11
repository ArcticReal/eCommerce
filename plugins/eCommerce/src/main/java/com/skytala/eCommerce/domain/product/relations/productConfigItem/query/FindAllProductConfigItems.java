
package com.skytala.eCommerce.domain.product.relations.productConfigItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.event.ProductConfigItemFound;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.mapper.ProductConfigItemMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.model.ProductConfigItem;


public class FindAllProductConfigItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigItem> returnVal = new ArrayList<ProductConfigItem>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
