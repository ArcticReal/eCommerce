
package com.skytala.eCommerce.domain.party.relations.party.query.group;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.group.PartyGroupFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.group.PartyGroupMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;


public class FindAllPartyGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyGroup> returnVal = new ArrayList<PartyGroup>();
try{
List<GenericValue> results = delegator.findAll("PartyGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
