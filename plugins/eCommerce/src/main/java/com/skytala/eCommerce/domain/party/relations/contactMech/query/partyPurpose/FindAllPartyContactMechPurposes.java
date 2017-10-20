
package com.skytala.eCommerce.domain.party.relations.contactMech.query.partyPurpose;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose.PartyContactMechPurposeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.partyPurpose.PartyContactMechPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.partyPurpose.PartyContactMechPurpose;


public class FindAllPartyContactMechPurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyContactMechPurpose> returnVal = new ArrayList<PartyContactMechPurpose>();
try{
List<GenericValue> results = delegator.findAll("PartyContactMechPurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyContactMechPurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyContactMechPurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
