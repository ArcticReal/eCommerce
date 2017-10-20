
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.note.InvoiceNoteFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.note.InvoiceNoteMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.note.InvoiceNote;


public class FindAllInvoiceNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceNote> returnVal = new ArrayList<InvoiceNote>();
try{
List<GenericValue> results = delegator.findAll("InvoiceNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
