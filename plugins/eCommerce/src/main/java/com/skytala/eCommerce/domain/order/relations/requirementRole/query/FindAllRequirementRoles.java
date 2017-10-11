
package com.skytala.eCommerce.domain.order.relations.requirementRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirementRole.event.RequirementRoleFound;
import com.skytala.eCommerce.domain.order.relations.requirementRole.mapper.RequirementRoleMapper;
import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;


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
