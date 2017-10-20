
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.gatewayDhl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl.ShipmentGatewayDhlFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayDhl.ShipmentGatewayDhlMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl.ShipmentGatewayDhl;


public class FindAllShipmentGatewayDhls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayDhl> returnVal = new ArrayList<ShipmentGatewayDhl>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayDhl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayDhlMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayDhlFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
