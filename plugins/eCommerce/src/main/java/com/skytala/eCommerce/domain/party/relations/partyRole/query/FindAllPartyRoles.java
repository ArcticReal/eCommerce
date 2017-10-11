
package com.skytala.eCommerce.domain.party.relations.partyRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyRole.event.PartyRoleFound;
import com.skytala.eCommerce.domain.party.relations.partyRole.mapper.PartyRoleMapper;
import com.skytala.eCommerce.domain.party.relations.partyRole.model.PartyRole;


public class FindAllPartyRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyRole> returnVal = new ArrayList<PartyRole>();
try{
List<GenericValue> results = delegator.findAll("PartyRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
