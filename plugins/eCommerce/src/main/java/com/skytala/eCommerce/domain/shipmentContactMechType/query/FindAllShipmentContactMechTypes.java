
package com.skytala.eCommerce.domain.shipmentContactMechType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipmentContactMechType.event.ShipmentContactMechTypeFound;
import com.skytala.eCommerce.domain.shipmentContactMechType.mapper.ShipmentContactMechTypeMapper;
import com.skytala.eCommerce.domain.shipmentContactMechType.model.ShipmentContactMechType;


public class FindAllShipmentContactMechTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentContactMechType> returnVal = new ArrayList<ShipmentContactMechType>();
try{
List<GenericValue> results = delegator.findAll("ShipmentContactMechType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentContactMechTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentContactMechTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
