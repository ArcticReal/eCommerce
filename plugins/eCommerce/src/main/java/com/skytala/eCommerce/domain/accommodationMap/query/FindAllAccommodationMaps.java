
package com.skytala.eCommerce.domain.accommodationMap.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accommodationMap.event.AccommodationMapFound;
import com.skytala.eCommerce.domain.accommodationMap.mapper.AccommodationMapMapper;
import com.skytala.eCommerce.domain.accommodationMap.model.AccommodationMap;


public class FindAllAccommodationMaps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AccommodationMap> returnVal = new ArrayList<AccommodationMap>();
try{
List<GenericValue> results = delegator.findAll("AccommodationMap", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AccommodationMapMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AccommodationMapFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
