
package com.skytala.eCommerce.domain.accommodationSpot.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accommodationSpot.event.AccommodationSpotFound;
import com.skytala.eCommerce.domain.accommodationSpot.mapper.AccommodationSpotMapper;
import com.skytala.eCommerce.domain.accommodationSpot.model.AccommodationSpot;


public class FindAllAccommodationSpots extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AccommodationSpot> returnVal = new ArrayList<AccommodationSpot>();
try{
List<GenericValue> results = delegator.findAll("AccommodationSpot", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AccommodationSpotMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AccommodationSpotFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
