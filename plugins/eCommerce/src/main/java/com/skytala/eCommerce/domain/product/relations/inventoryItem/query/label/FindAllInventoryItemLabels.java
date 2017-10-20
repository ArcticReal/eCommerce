
package com.skytala.eCommerce.domain.product.relations.inventoryItem.query.label;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.label.InventoryItemLabelMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;


public class FindAllInventoryItemLabels extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemLabel> returnVal = new ArrayList<InventoryItemLabel>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemLabel", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemLabelMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemLabelFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
