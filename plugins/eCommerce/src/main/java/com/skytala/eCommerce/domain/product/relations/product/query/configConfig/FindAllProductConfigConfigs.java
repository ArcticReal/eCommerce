
package com.skytala.eCommerce.domain.product.relations.product.query.configConfig;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configConfig.ProductConfigConfigMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;


public class FindAllProductConfigConfigs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigConfig> returnVal = new ArrayList<ProductConfigConfig>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigConfig", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigConfigMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigConfigFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
