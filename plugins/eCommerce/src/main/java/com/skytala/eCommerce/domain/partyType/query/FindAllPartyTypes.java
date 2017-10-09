
package com.skytala.eCommerce.domain.partyType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.partyType.event.PartyTypeFound;
import com.skytala.eCommerce.domain.partyType.mapper.PartyTypeMapper;
import com.skytala.eCommerce.domain.partyType.model.PartyType;


public class FindAllPartyTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyType> returnVal = new ArrayList<PartyType>();
try{
List<GenericValue> results = delegator.findAll("PartyType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}