
package com.skytala.eCommerce.domain.humanres.relations.partyResume.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeFound;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.mapper.PartyResumeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;


public class FindAllPartyResumes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyResume> returnVal = new ArrayList<PartyResume>();
try{
List<GenericValue> results = delegator.findAll("PartyResume", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyResumeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyResumeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
