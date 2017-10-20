
package com.skytala.eCommerce.domain.product.relations.facility.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.type.FacilityTypeFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.type.FacilityTypeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.type.FacilityType;


public class FindAllFacilityTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityType> returnVal = new ArrayList<FacilityType>();
try{
List<GenericValue> results = delegator.findAll("FacilityType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
