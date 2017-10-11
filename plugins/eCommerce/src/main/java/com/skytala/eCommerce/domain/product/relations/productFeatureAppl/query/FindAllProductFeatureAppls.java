
package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event.ProductFeatureApplFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.mapper.ProductFeatureApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;


public class FindAllProductFeatureAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureAppl> returnVal = new ArrayList<ProductFeatureAppl>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
