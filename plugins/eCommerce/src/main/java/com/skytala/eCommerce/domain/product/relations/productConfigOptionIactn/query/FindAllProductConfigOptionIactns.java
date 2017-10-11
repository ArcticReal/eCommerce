
package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.event.ProductConfigOptionIactnFound;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.mapper.ProductConfigOptionIactnMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;


public class FindAllProductConfigOptionIactns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigOptionIactn> returnVal = new ArrayList<ProductConfigOptionIactn>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigOptionIactn", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigOptionIactnMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigOptionIactnFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
