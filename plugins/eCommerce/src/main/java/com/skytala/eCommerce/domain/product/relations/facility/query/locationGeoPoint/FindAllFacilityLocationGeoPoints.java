
package com.skytala.eCommerce.domain.product.relations.facility.query.locationGeoPoint;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint.FacilityLocationGeoPointFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.locationGeoPoint.FacilityLocationGeoPointMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;


public class FindAllFacilityLocationGeoPoints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityLocationGeoPoint> returnVal = new ArrayList<FacilityLocationGeoPoint>();
try{
List<GenericValue> results = delegator.findAll("FacilityLocationGeoPoint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityLocationGeoPointMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityLocationGeoPointFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
