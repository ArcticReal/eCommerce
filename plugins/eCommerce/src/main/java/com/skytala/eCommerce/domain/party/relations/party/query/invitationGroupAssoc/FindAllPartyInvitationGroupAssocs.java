
package com.skytala.eCommerce.domain.party.relations.party.query.invitationGroupAssoc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc.PartyInvitationGroupAssocFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationGroupAssoc.PartyInvitationGroupAssocMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;


public class FindAllPartyInvitationGroupAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyInvitationGroupAssoc> returnVal = new ArrayList<PartyInvitationGroupAssoc>();
try{
List<GenericValue> results = delegator.findAll("PartyInvitationGroupAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyInvitationGroupAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyInvitationGroupAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
