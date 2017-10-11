
package com.skytala.eCommerce.domain.product.relations.productFacility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productFacility.event.ProductFacilityFound;
import com.skytala.eCommerce.domain.product.relations.productFacility.mapper.ProductFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.productFacility.model.ProductFacility;


public class FindAllProductFacilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFacility> returnVal = new ArrayList<ProductFacility>();
try{
List<GenericValue> results = delegator.findAll("ProductFacility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFacilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFacilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
