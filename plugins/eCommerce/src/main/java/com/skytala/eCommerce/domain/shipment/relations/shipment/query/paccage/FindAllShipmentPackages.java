
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.paccage;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.paccage.ShipmentPackageFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.paccage.ShipmentPackageMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.paccage.ShipmentPackage;


public class FindAllShipmentPackages extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentPackage> returnVal = new ArrayList<ShipmentPackage>();
try{
List<GenericValue> results = delegator.findAll("ShipmentPackage", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentPackageMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentPackageFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
