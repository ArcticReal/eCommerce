
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.packageRouteSeg;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.packageRouteSeg.ShipmentPackageRouteSegMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;


public class FindAllShipmentPackageRouteSegs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentPackageRouteSeg> returnVal = new ArrayList<ShipmentPackageRouteSeg>();
try{
List<GenericValue> results = delegator.findAll("ShipmentPackageRouteSeg", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentPackageRouteSegMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentPackageRouteSegFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
