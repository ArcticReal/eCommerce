
package com.skytala.eCommerce.domain.party.relations.validContactMechRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.validContactMechRole.event.ValidContactMechRoleFound;
import com.skytala.eCommerce.domain.party.relations.validContactMechRole.mapper.ValidContactMechRoleMapper;
import com.skytala.eCommerce.domain.party.relations.validContactMechRole.model.ValidContactMechRole;


public class FindAllValidContactMechRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ValidContactMechRole> returnVal = new ArrayList<ValidContactMechRole>();
try{
List<GenericValue> results = delegator.findAll("ValidContactMechRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ValidContactMechRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ValidContactMechRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
