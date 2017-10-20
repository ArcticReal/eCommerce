
package com.skytala.eCommerce.domain.party.relations.party.query.status;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.status.PartyStatusFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.status.PartyStatusMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;


public class FindAllPartyStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyStatus> returnVal = new ArrayList<PartyStatus>();
try{
List<GenericValue> results = delegator.findAll("PartyStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
