
package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event.ShipmentItemBillingFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.mapper.ShipmentItemBillingMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;


public class FindAllShipmentItemBillings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentItemBilling> returnVal = new ArrayList<ShipmentItemBilling>();
try{
List<GenericValue> results = delegator.findAll("ShipmentItemBilling", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentItemBillingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentItemBillingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
