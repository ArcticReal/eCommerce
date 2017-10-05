
package com.skytala.eCommerce.domain.productFeature.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productFeature.event.ProductFeatureFound;
import com.skytala.eCommerce.domain.productFeature.mapper.ProductFeatureMapper;
import com.skytala.eCommerce.domain.productFeature.model.ProductFeature;


public class FindAllProductFeatures extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeature> returnVal = new ArrayList<ProductFeature>();
try{
List<GenericValue> results = delegator.findAll("ProductFeature", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
