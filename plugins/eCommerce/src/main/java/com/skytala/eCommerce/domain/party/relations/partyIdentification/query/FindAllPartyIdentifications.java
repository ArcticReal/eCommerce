
package com.skytala.eCommerce.domain.party.relations.partyIdentification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.event.PartyIdentificationFound;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.mapper.PartyIdentificationMapper;
import com.skytala.eCommerce.domain.party.relations.partyIdentification.model.PartyIdentification;


public class FindAllPartyIdentifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyIdentification> returnVal = new ArrayList<PartyIdentification>();
try{
List<GenericValue> results = delegator.findAll("PartyIdentification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyIdentificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyIdentificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
