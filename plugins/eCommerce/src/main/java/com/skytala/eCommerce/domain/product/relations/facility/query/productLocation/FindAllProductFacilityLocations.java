
package com.skytala.eCommerce.domain.product.relations.facility.query.productLocation;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.productLocation.ProductFacilityLocationFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.productLocation.ProductFacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.productLocation.ProductFacilityLocation;


public class FindAllProductFacilityLocations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductFacilityLocation> returnVal = new ArrayList<ProductFacilityLocation>();
try{
List<GenericValue> results = delegator.findAll("ProductFacilityLocation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductFacilityLocationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductFacilityLocationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
