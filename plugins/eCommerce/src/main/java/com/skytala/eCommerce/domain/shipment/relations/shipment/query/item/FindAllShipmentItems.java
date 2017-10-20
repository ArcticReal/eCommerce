
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.item.ShipmentItemFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.item.ShipmentItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;


public class FindAllShipmentItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentItem> returnVal = new ArrayList<ShipmentItem>();
try{
List<GenericValue> results = delegator.findAll("ShipmentItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
