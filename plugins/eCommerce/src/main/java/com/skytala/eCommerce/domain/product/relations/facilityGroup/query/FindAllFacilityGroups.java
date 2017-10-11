
package com.skytala.eCommerce.domain.product.relations.facilityGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.event.FacilityGroupFound;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.mapper.FacilityGroupMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.model.FacilityGroup;


public class FindAllFacilityGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityGroup> returnVal = new ArrayList<FacilityGroup>();
try{
List<GenericValue> results = delegator.findAll("FacilityGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
