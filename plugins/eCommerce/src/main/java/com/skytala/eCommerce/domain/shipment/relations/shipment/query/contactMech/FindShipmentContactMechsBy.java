package com.skytala.eCommerce.domain.shipment.relations.shipment.query.contactMech;
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
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMech.ShipmentContactMechMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;

public class FindShipmentContactMechsBy extends Query {


Map<String, String> filter;
public FindShipmentContactMechsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentContactMech> foundShipmentContactMechs = new ArrayList<ShipmentContactMech>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("shipmentContactMechId")) { 
 GenericValue foundElement = delegator.findOne("ShipmentContactMech", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ShipmentContactMech.class); 
 } 
}else { 
 buf = delegator.findAll("ShipmentContactMech", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundShipmentContactMechs.add(ShipmentContactMechMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ShipmentContactMechFound(foundShipmentContactMechs);
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
