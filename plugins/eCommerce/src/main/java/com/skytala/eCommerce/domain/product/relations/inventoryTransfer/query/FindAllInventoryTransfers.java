
package com.skytala.eCommerce.domain.product.relations.inventoryTransfer.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferFound;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.mapper.InventoryTransferMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model.InventoryTransfer;


public class FindAllInventoryTransfers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryTransfer> returnVal = new ArrayList<InventoryTransfer>();
try{
List<GenericValue> results = delegator.findAll("InventoryTransfer", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryTransferMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryTransferFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
