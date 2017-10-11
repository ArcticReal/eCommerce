
package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.event.PartyIcsAvsOverrideFound;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.mapper.PartyIcsAvsOverrideMapper;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;


public class FindAllPartyIcsAvsOverrides extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyIcsAvsOverride> returnVal = new ArrayList<PartyIcsAvsOverride>();
try{
List<GenericValue> results = delegator.findAll("PartyIcsAvsOverride", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyIcsAvsOverrideMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyIcsAvsOverrideFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
