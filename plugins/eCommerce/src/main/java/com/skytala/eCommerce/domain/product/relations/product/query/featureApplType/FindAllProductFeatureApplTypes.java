
package com.skytala.eCommerce.domain.product.relations.product.query.featureApplType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplType.ProductFeatureApplTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplType.ProductFeatureApplTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;


public class FindAllProductFeatureApplTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureApplType> returnVal = new ArrayList<ProductFeatureApplType>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureApplType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureApplTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureApplTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
