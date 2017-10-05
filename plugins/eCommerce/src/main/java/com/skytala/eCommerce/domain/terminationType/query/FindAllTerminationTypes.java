
package com.skytala.eCommerce.domain.terminationType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.terminationType.event.TerminationTypeFound;
import com.skytala.eCommerce.domain.terminationType.mapper.TerminationTypeMapper;
import com.skytala.eCommerce.domain.terminationType.model.TerminationType;


public class FindAllTerminationTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TerminationType> returnVal = new ArrayList<TerminationType>();
try{
List<GenericValue> results = delegator.findAll("TerminationType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TerminationTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TerminationTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
