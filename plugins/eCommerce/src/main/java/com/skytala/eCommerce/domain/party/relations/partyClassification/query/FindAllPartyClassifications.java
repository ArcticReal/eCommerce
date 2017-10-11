
package com.skytala.eCommerce.domain.party.relations.partyClassification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyClassification.event.PartyClassificationFound;
import com.skytala.eCommerce.domain.party.relations.partyClassification.mapper.PartyClassificationMapper;
import com.skytala.eCommerce.domain.party.relations.partyClassification.model.PartyClassification;


public class FindAllPartyClassifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyClassification> returnVal = new ArrayList<PartyClassification>();
try{
List<GenericValue> results = delegator.findAll("PartyClassification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyClassificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyClassificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
