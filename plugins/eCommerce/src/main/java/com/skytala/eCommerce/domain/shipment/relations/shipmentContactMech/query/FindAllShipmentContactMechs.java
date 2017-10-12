
package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.event.ShipmentContactMechFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.mapper.ShipmentContactMechMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.model.ShipmentContactMech;


public class FindAllShipmentContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentContactMech> returnVal = new ArrayList<ShipmentContactMech>();
try{
List<GenericValue> results = delegator.findAll("ShipmentContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
