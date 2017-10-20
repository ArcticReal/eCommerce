
package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type.AccommodationMapTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.mapper.type.AccommodationMapTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type.AccommodationMapType;


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
