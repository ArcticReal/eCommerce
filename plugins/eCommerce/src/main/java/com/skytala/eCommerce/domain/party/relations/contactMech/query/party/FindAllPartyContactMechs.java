
package com.skytala.eCommerce.domain.party.relations.contactMech.query.party;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.party.PartyContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;


public class FindAllPartyContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyContactMech> returnVal = new ArrayList<PartyContactMech>();
try{
List<GenericValue> results = delegator.findAll("PartyContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
