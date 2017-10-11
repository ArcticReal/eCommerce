
package com.skytala.eCommerce.domain.product.relations.productConfigOption.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productConfigOption.event.ProductConfigOptionFound;
import com.skytala.eCommerce.domain.product.relations.productConfigOption.mapper.ProductConfigOptionMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigOption.model.ProductConfigOption;


public class FindAllProductConfigOptions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductConfigOption> returnVal = new ArrayList<ProductConfigOption>();
try{
List<GenericValue> results = delegator.findAll("ProductConfigOption", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductConfigOptionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductConfigOptionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
