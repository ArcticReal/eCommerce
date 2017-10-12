
package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event.InvoiceNoteFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.mapper.InvoiceNoteMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;


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
