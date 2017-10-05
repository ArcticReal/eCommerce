
package com.skytala.eCommerce.domain.productFeatureCategory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.productFeatureCategory.event.ProductFeatureCategoryFound;
import com.skytala.eCommerce.domain.productFeatureCategory.mapper.ProductFeatureCategoryMapper;
import com.skytala.eCommerce.domain.productFeatureCategory.model.ProductFeatureCategory;


public class FindAllProductFeatureCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureCategory> returnVal = new ArrayList<ProductFeatureCategory>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
