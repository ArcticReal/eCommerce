
package com.skytala.eCommerce.domain.shipmentGatewayConfig.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipmentGatewayConfig.event.ShipmentGatewayConfigFound;
import com.skytala.eCommerce.domain.shipmentGatewayConfig.mapper.ShipmentGatewayConfigMapper;
import com.skytala.eCommerce.domain.shipmentGatewayConfig.model.ShipmentGatewayConfig;


public class FindAllShipmentGatewayConfigs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentGatewayConfig> returnVal = new ArrayList<ShipmentGatewayConfig>();
try{
List<GenericValue> results = delegator.findAll("ShipmentGatewayConfig", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentGatewayConfigMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentGatewayConfigFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
