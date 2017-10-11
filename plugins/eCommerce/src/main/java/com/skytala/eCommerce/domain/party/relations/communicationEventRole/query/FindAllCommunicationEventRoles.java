
package com.skytala.eCommerce.domain.party.relations.communicationEventRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.event.CommunicationEventRoleFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.mapper.CommunicationEventRoleMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventRole.model.CommunicationEventRole;


public class FindAllCommunicationEventRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventRole> returnVal = new ArrayList<CommunicationEventRole>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
