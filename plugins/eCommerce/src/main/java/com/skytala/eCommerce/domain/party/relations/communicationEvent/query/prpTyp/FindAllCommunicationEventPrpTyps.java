
package com.skytala.eCommerce.domain.party.relations.communicationEvent.query.prpTyp;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp.CommunicationEventPrpTypFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.prpTyp.CommunicationEventPrpTypMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;


public class FindAllCommunicationEventPrpTyps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventPrpTyp> returnVal = new ArrayList<CommunicationEventPrpTyp>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventPrpTyp", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventPrpTypMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventPrpTypFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
