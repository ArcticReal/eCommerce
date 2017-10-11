
package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.event.InventoryItemLabelApplFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.mapper.InventoryItemLabelApplMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model.InventoryItemLabelAppl;


public class FindAllInventoryItemLabelAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemLabelAppl> returnVal = new ArrayList<InventoryItemLabelAppl>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemLabelAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemLabelApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemLabelApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
