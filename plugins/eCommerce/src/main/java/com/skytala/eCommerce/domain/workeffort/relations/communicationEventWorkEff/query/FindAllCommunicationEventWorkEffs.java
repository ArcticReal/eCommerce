
package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event.CommunicationEventWorkEffFound;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.mapper.CommunicationEventWorkEffMapper;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;


public class FindAllCommunicationEventWorkEffs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventWorkEff> returnVal = new ArrayList<CommunicationEventWorkEff>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventWorkEff", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventWorkEffMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventWorkEffFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
