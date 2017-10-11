
package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.event.FacilityGroupRoleFound;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.mapper.FacilityGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;


public class FindAllFacilityGroupRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityGroupRole> returnVal = new ArrayList<FacilityGroupRole>();
try{
List<GenericValue> results = delegator.findAll("FacilityGroupRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityGroupRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityGroupRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
