
package com.skytala.eCommerce.domain.order.relations.requirement.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.role.RequirementRoleFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.role.RequirementRoleMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.role.RequirementRole;


public class FindAllRequirementRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementRole> returnVal = new ArrayList<RequirementRole>();
try{
List<GenericValue> results = delegator.findAll("RequirementRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
