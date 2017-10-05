
package com.skytala.eCommerce.domain.facilityGroupType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.facilityGroupType.event.FacilityGroupTypeFound;
import com.skytala.eCommerce.domain.facilityGroupType.mapper.FacilityGroupTypeMapper;
import com.skytala.eCommerce.domain.facilityGroupType.model.FacilityGroupType;


public class FindAllFacilityGroupTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityGroupType> returnVal = new ArrayList<FacilityGroupType>();
try{
List<GenericValue> results = delegator.findAll("FacilityGroupType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityGroupTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityGroupTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
