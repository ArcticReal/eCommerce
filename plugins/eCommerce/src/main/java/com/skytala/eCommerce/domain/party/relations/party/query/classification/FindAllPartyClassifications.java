
package com.skytala.eCommerce.domain.party.relations.party.query.classification;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.classification.PartyClassificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;


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
