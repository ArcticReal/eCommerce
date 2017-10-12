
package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.mapper.ShipmentGatewayFedexMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;


public class FindAllShipmentGatewayFedexs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayFedex> returnVal = new ArrayList<ShipmentGatewayFedex>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayFedex", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayFedexMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayFedexFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
