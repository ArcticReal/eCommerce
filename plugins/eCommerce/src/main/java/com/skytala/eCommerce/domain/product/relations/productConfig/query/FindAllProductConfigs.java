
package com.skytala.eCommerce.domain.product.relations.productConfig.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productConfig.event.ProductConfigFound;
import com.skytala.eCommerce.domain.product.relations.productConfig.mapper.ProductConfigMapper;
import com.skytala.eCommerce.domain.product.relations.productConfig.model.ProductConfig;


public class FindAllProductConfigs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfig> returnVal = new ArrayList<ProductConfig>();
try{
List<GenericValue> results = delegator.findAll("ProductConfig", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
