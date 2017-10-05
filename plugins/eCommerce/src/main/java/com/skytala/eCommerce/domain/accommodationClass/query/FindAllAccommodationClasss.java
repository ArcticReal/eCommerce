
package com.skytala.eCommerce.domain.accommodationClass.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accommodationClass.event.AccommodationClassFound;
import com.skytala.eCommerce.domain.accommodationClass.mapper.AccommodationClassMapper;
import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;


public class FindAllAccommodationClasss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AccommodationClass> returnVal = new ArrayList<AccommodationClass>();
try{
List<GenericValue> results = delegator.findAll("AccommodationClass", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AccommodationClassMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AccommodationClassFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
