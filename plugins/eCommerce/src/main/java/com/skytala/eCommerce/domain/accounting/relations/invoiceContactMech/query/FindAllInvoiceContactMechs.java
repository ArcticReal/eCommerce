
package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event.InvoiceContactMechFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.mapper.InvoiceContactMechMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;


public class FindAllInvoiceContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceContactMech> returnVal = new ArrayList<InvoiceContactMech>();
try{
List<GenericValue> results = delegator.findAll("InvoiceContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
