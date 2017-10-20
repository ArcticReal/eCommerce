
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.boxType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.boxType.ShipmentBoxTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.boxType.ShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.boxType.ShipmentBoxType;


public class FindAllShipmentBoxTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentBoxType> returnVal = new ArrayList<ShipmentBoxType>();
try{
List<GenericValue> results = delegator.findAll("ShipmentBoxType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentBoxTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentBoxTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
