
package com.skytala.eCommerce.domain.shipmentMethodType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipmentMethodType.event.ShipmentMethodTypeFound;
import com.skytala.eCommerce.domain.shipmentMethodType.mapper.ShipmentMethodTypeMapper;
import com.skytala.eCommerce.domain.shipmentMethodType.model.ShipmentMethodType;


public class FindAllShipmentMethodTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentMethodType> returnVal = new ArrayList<ShipmentMethodType>();
try{
List<GenericValue> results = delegator.findAll("ShipmentMethodType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentMethodTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentMethodTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
