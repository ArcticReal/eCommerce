
package com.skytala.eCommerce.domain.party.relations.party.query.icsAvsOverride;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.icsAvsOverride.PartyIcsAvsOverrideFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.icsAvsOverride.PartyIcsAvsOverrideMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.icsAvsOverride.PartyIcsAvsOverride;


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
