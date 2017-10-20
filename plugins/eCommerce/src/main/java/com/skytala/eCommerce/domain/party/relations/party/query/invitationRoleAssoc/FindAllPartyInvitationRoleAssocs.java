
package com.skytala.eCommerce.domain.party.relations.party.query.invitationRoleAssoc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc.PartyInvitationRoleAssocFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc.PartyInvitationRoleAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;


public class FindAllPartyInvitationRoleAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyInvitationRoleAssoc> returnVal = new ArrayList<PartyInvitationRoleAssoc>();
try{
List<GenericValue> results = delegator.findAll("PartyInvitationRoleAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyInvitationRoleAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyInvitationRoleAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
