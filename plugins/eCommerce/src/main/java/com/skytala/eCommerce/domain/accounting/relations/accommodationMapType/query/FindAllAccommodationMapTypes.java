
package com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.event.AccommodationMapTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.mapper.AccommodationMapTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMapType.model.AccommodationMapType;


public class FindAllAccommodationMapTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AccommodationMapType> returnVal = new ArrayList<AccommodationMapType>();
try{
List<GenericValue> results = delegator.findAll("AccommodationMapType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AccommodationMapTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AccommodationMapTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
