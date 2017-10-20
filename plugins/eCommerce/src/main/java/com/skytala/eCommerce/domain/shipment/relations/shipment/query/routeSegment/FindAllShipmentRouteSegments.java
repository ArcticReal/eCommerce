
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.routeSegment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.routeSegment.ShipmentRouteSegmentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;


public class FindAllShipmentRouteSegments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentRouteSegment> returnVal = new ArrayList<ShipmentRouteSegment>();
try{
List<GenericValue> results = delegator.findAll("ShipmentRouteSegment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentRouteSegmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentRouteSegmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}