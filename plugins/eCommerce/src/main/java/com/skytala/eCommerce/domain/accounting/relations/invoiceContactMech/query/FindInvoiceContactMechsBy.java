package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.query;
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
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event.InvoiceContactMechAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event.InvoiceContactMechFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.mapper.InvoiceContactMechMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;

public class FindInvoiceContactMechsBy extends Query {


Map<String, String> filter;
public FindInvoiceContactMechsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceContactMech> foundInvoiceContactMechs = new ArrayList<InvoiceContactMech>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("invoiceContactMechId")) { 
 GenericValue foundElement = delegator.findOne("InvoiceContactMech", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(InvoiceContactMech.class); 
 } 
}else { 
 buf = delegator.findAll("InvoiceContactMech", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundInvoiceContactMechs.add(InvoiceContactMechMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new InvoiceContactMechFound(foundInvoiceContactMechs);
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
