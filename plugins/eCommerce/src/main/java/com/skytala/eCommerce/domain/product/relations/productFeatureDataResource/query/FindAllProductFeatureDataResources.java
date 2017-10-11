
package com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.event.ProductFeatureDataResourceFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.mapper.ProductFeatureDataResourceMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.model.ProductFeatureDataResource;


public class FindAllProductFeatureDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureDataResource> returnVal = new ArrayList<ProductFeatureDataResource>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureDataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureDataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureDataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
