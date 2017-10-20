
package com.skytala.eCommerce.domain.humanres.relations.emplLeave.query.reasonType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.event.reasonType.EmplLeaveReasonTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.reasonType.EmplLeaveReasonTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType.EmplLeaveReasonType;


public class FindAllEmplLeaveReasonTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplLeaveReasonType> returnVal = new ArrayList<EmplLeaveReasonType>();
try{
List<GenericValue> results = delegator.findAll("EmplLeaveReasonType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplLeaveReasonTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplLeaveReasonTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
