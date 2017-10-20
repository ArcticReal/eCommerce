
package com.skytala.eCommerce.domain.product.relations.product.query.configStats;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configStats.ProductConfigStatsMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;


public class FindAllProductConfigStatss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigStats> returnVal = new ArrayList<ProductConfigStats>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigStats", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigStatsMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigStatsFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
