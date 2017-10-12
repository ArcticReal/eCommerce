package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event.ShipmentItemBillingAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event.ShipmentItemBillingFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.mapper.ShipmentItemBillingMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;

public class FindShipmentItemBillingsBy extends Query {


Map<String, String> filter;
public FindShipmentItemBillingsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentItemBilling> foundShipmentItemBillings = new ArrayList<ShipmentItemBilling>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("shipmentItemBillingId")) { 
 GenericValue foundElement = delegator.findOne("ShipmentItemBilling", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ShipmentItemBilling.class); 
 } 
}else { 
 buf = delegator.findAll("ShipmentItemBilling", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundShipmentItemBillings.add(ShipmentItemBillingMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ShipmentItemBillingFound(foundShipmentItemBillings);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}
