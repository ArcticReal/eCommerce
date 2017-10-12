
package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.event.ShipmentGatewayUpsFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.mapper.ShipmentGatewayUpsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model.ShipmentGatewayUps;


public class FindAllShipmentGatewayUpss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayUps> returnVal = new ArrayList<ShipmentGatewayUps>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayUps", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayUpsMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayUpsFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
