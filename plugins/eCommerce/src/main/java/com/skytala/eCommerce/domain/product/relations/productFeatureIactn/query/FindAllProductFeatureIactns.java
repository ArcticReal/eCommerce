
package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.mapper.ProductFeatureIactnMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;


public class FindAllProductFeatureIactns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFeatureIactn> returnVal = new ArrayList<ProductFeatureIactn>();
try{
List<GenericValue> results = delegator.findAll("ProductFeatureIactn", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFeatureIactnMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFeatureIactnFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
