
package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event.ShipmentStatusFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.mapper.ShipmentStatusMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;


public class FindAllShipmentStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentStatus> returnVal = new ArrayList<ShipmentStatus>();
try{
List<GenericValue> results = delegator.findAll("ShipmentStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
