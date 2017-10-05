
package com.skytala.eCommerce.domain.addendum.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.addendum.event.AddendumFound;
import com.skytala.eCommerce.domain.addendum.mapper.AddendumMapper;
import com.skytala.eCommerce.domain.addendum.model.Addendum;


public class FindAllAddendums extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Addendum> returnVal = new ArrayList<Addendum>();
try{
List<GenericValue> results = delegator.findAll("Addendum", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AddendumMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AddendumFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
