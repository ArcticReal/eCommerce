
package com.skytala.eCommerce.domain.partyClassificationGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.partyClassificationGroup.event.PartyClassificationGroupFound;
import com.skytala.eCommerce.domain.partyClassificationGroup.mapper.PartyClassificationGroupMapper;
import com.skytala.eCommerce.domain.partyClassificationGroup.model.PartyClassificationGroup;


public class FindAllPartyClassificationGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyClassificationGroup> returnVal = new ArrayList<PartyClassificationGroup>();
try{
List<GenericValue> results = delegator.findAll("PartyClassificationGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyClassificationGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyClassificationGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
