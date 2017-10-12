package com.skytala.eCommerce.domain.accounting.relations.invoiceType.query;
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
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.event.InvoiceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.event.InvoiceTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.mapper.InvoiceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;

public class FindInvoiceTypesBy extends Query {


Map<String, String> filter;
public FindInvoiceTypesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceType> foundInvoiceTypes = new ArrayList<InvoiceType>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("invoiceTypeId")) { 
 GenericValue foundElement = delegator.findOne("InvoiceType", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(InvoiceType.class); 
 } 
}else { 
 buf = delegator.findAll("InvoiceType", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundInvoiceTypes.add(InvoiceTypeMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new InvoiceTypeFound(foundInvoiceTypes);
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
