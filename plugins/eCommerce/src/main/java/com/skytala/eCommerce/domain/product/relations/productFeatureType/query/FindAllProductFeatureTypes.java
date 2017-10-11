
package com.skytala.eCommerce.domain.product.relations.productFeatureType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureType.event.ProductFeatureTypeFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureType.mapper.ProductFeatureTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureType.model.ProductFeatureType;


public class FindAllProductFeatureTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureType> returnVal = new ArrayList<ProductFeatureType>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
