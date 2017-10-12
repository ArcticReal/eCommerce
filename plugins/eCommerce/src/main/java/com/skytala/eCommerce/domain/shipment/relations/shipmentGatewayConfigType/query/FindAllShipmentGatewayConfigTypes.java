
package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.event.ShipmentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.mapper.ShipmentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.model.ShipmentGatewayConfigType;


public class FindAllShipmentGatewayConfigTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayConfigType> returnVal = new ArrayList<ShipmentGatewayConfigType>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayConfigType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayConfigTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayConfigTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
