
package com.skytala.eCommerce.domain.product.relations.facility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.FacilityFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.FacilityMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;


public class FindAllFacilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Facility> returnVal = new ArrayList<Facility>();
try{
List<GenericValue> results = delegator.findAll("Facility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
