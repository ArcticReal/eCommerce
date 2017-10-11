
package com.skytala.eCommerce.domain.product.relations.facilityContactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.event.FacilityContactMechFound;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.mapper.FacilityContactMechMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;


public class FindAllFacilityContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityContactMech> returnVal = new ArrayList<FacilityContactMech>();
try{
List<GenericValue> results = delegator.findAll("FacilityContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
