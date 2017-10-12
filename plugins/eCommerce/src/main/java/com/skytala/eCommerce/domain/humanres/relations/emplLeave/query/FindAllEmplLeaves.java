
package com.skytala.eCommerce.domain.humanres.relations.emplLeave.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.EmplLeaveFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.EmplLeaveMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;


public class FindAllEmplLeaves extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplLeave> returnVal = new ArrayList<EmplLeave>();
try{
List<GenericValue> results = delegator.findAll("EmplLeave", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplLeaveMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplLeaveFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
