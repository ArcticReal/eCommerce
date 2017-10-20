
package com.skytala.eCommerce.domain.product.relations.facility.query.groupRollup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup.FacilityGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.groupRollup.FacilityGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupRollup.FacilityGroupRollup;


public class FindAllFacilityGroupRollups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityGroupRollup> returnVal = new ArrayList<FacilityGroupRollup>();
try{
List<GenericValue> results = delegator.findAll("FacilityGroupRollup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityGroupRollupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityGroupRollupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
