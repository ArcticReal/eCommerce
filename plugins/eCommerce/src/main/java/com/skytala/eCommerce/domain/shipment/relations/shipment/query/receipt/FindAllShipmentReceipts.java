
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.receipt;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt.ShipmentReceiptFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receipt.ShipmentReceiptMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;


public class FindAllShipmentReceipts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentReceipt> returnVal = new ArrayList<ShipmentReceipt>();
try{
List<GenericValue> results = delegator.findAll("ShipmentReceipt", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentReceiptMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentReceiptFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
