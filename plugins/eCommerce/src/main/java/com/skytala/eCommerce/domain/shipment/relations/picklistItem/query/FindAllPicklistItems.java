
package com.skytala.eCommerce.domain.shipment.relations.picklistItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.event.PicklistItemFound;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.mapper.PicklistItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.model.PicklistItem;


public class FindAllPicklistItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PicklistItem> returnVal = new ArrayList<PicklistItem>();
try{
List<GenericValue> results = delegator.findAll("PicklistItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PicklistItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PicklistItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
