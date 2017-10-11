
package com.skytala.eCommerce.domain.product.relations.productStoreFacility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.event.ProductStoreFacilityFound;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.mapper.ProductStoreFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.model.ProductStoreFacility;


public class FindAllProductStoreFacilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreFacility> returnVal = new ArrayList<ProductStoreFacility>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreFacility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreFacilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreFacilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
