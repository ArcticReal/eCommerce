
package com.skytala.eCommerce.domain.party.relations.roleType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeFound;
import com.skytala.eCommerce.domain.party.relations.roleType.mapper.RoleTypeMapper;
import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;


public class FindAllRoleTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RoleType> returnVal = new ArrayList<RoleType>();
try{
List<GenericValue> results = delegator.findAll("RoleType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RoleTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RoleTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
