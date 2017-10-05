
package com.skytala.eCommerce.domain.invoice.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.invoice.event.InvoiceFound;
import com.skytala.eCommerce.domain.invoice.mapper.InvoiceMapper;
import com.skytala.eCommerce.domain.invoice.model.Invoice;


public class FindAllInvoices extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Invoice> returnVal = new ArrayList<Invoice>();
try{
List<GenericValue> results = delegator.findAll("Invoice", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
