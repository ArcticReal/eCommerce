
package com.skytala.eCommerce.domain.party.relations.communicationEvent.query.purpose;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose.CommunicationEventPurposeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.purpose.CommunicationEventPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;


public class FindAllCommunicationEventPurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventPurpose> returnVal = new ArrayList<CommunicationEventPurpose>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventPurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventPurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventPurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
