
package com.skytala.eCommerce.domain.product.relations.product.query.featureGroup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroup.ProductFeatureGroupFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroup.ProductFeatureGroupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;


public class FindAllProductFeatureGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureGroup> returnVal = new ArrayList<ProductFeatureGroup>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
