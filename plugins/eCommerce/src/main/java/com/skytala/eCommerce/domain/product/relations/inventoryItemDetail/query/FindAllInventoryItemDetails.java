
package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.mapper.InventoryItemDetailMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model.InventoryItemDetail;


public class FindAllInventoryItemDetails extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InventoryItemDetail> returnVal = new ArrayList<InventoryItemDetail>();
try{
List<GenericValue> results = delegator.findAll("InventoryItemDetail", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InventoryItemDetailMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InventoryItemDetailFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
