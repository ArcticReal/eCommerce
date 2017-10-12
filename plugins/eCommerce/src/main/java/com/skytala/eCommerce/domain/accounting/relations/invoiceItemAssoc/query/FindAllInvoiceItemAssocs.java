
package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.event.InvoiceItemAssocFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.mapper.InvoiceItemAssocMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.model.InvoiceItemAssoc;


public class FindAllInvoiceItemAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemAssoc> returnVal = new ArrayList<InvoiceItemAssoc>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
