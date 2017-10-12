
package com.skytala.eCommerce.domain.humanres.relations.terminationReason.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.event.TerminationReasonFound;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.mapper.TerminationReasonMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;


public class FindAllTerminationReasons extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TerminationReason> returnVal = new ArrayList<TerminationReason>();
try{
List<GenericValue> results = delegator.findAll("TerminationReason", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TerminationReasonMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TerminationReasonFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
