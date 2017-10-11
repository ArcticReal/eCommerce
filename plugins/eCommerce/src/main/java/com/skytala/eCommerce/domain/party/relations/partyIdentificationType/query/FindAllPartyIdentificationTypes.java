
package com.skytala.eCommerce.domain.party.relations.partyIdentificationType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.event.PartyIdentificationTypeFound;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.mapper.PartyIdentificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.model.PartyIdentificationType;


public class FindAllPartyIdentificationTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyIdentificationType> returnVal = new ArrayList<PartyIdentificationType>();
try{
List<GenericValue> results = delegator.findAll("PartyIdentificationType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyIdentificationTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyIdentificationTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
