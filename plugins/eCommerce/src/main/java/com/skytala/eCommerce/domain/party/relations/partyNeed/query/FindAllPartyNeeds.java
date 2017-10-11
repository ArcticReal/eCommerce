
package com.skytala.eCommerce.domain.party.relations.partyNeed.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyNeed.event.PartyNeedFound;
import com.skytala.eCommerce.domain.party.relations.partyNeed.mapper.PartyNeedMapper;
import com.skytala.eCommerce.domain.party.relations.partyNeed.model.PartyNeed;


public class FindAllPartyNeeds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyNeed> returnVal = new ArrayList<PartyNeed>();
try{
List<GenericValue> results = delegator.findAll("PartyNeed", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyNeedMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyNeedFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
