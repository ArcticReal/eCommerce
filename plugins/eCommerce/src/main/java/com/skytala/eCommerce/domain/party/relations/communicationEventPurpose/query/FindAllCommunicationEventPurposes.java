
package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event.CommunicationEventPurposeFound;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.mapper.CommunicationEventPurposeMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;


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
