
package com.skytala.eCommerce.domain.partyClassificationType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.partyClassificationType.event.PartyClassificationTypeFound;
import com.skytala.eCommerce.domain.partyClassificationType.mapper.PartyClassificationTypeMapper;
import com.skytala.eCommerce.domain.partyClassificationType.model.PartyClassificationType;


public class FindAllPartyClassificationTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyClassificationType> returnVal = new ArrayList<PartyClassificationType>();
try{
List<GenericValue> results = delegator.findAll("PartyClassificationType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyClassificationTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyClassificationTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}