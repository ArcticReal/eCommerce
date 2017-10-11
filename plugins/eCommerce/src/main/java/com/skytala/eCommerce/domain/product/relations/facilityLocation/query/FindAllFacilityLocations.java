
package com.skytala.eCommerce.domain.product.relations.facilityLocation.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.event.FacilityLocationFound;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.mapper.FacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;


public class FindAllFacilityLocations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityLocation> returnVal = new ArrayList<FacilityLocation>();
try{
List<GenericValue> results = delegator.findAll("FacilityLocation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityLocationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityLocationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
