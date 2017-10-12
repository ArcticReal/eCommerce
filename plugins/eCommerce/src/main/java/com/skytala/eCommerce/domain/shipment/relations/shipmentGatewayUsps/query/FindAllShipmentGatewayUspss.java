
package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.event.ShipmentGatewayUspsFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.mapper.ShipmentGatewayUspsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.model.ShipmentGatewayUsps;


public class FindAllShipmentGatewayUspss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayUsps> returnVal = new ArrayList<ShipmentGatewayUsps>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayUsps", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayUspsMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayUspsFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
