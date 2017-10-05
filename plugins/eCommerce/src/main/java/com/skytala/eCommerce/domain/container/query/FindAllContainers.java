
package com.skytala.eCommerce.domain.container.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.container.event.ContainerFound;
import com.skytala.eCommerce.domain.container.mapper.ContainerMapper;
import com.skytala.eCommerce.domain.container.model.Container;


public class FindAllContainers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Container> returnVal = new ArrayList<Container>();
try{
List<GenericValue> results = delegator.findAll("Container", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContainerMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContainerFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
