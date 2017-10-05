
package com.skytala.eCommerce.domain.partyInvitation.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.partyInvitation.event.PartyInvitationFound;
import com.skytala.eCommerce.domain.partyInvitation.mapper.PartyInvitationMapper;
import com.skytala.eCommerce.domain.partyInvitation.model.PartyInvitation;


public class FindAllPartyInvitations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyInvitation> returnVal = new ArrayList<PartyInvitation>();
try{
List<GenericValue> results = delegator.findAll("PartyInvitation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyInvitationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyInvitationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
