package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.query;
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
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event.InvoiceNoteAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event.InvoiceNoteFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.mapper.InvoiceNoteMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;

public class FindInvoiceNotesBy extends Query {


Map<String, String> filter;
public FindInvoiceNotesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceNote> foundInvoiceNotes = new ArrayList<InvoiceNote>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("invoiceNoteId")) { 
 GenericValue foundElement = delegator.findOne("InvoiceNote", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(InvoiceNote.class); 
 } 
}else { 
 buf = delegator.findAll("InvoiceNote", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundInvoiceNotes.add(InvoiceNoteMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new InvoiceNoteFound(foundInvoiceNotes);
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
