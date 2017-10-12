
package com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.event.InvoiceTermFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.mapper.InvoiceTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.model.InvoiceTerm;


public class FindAllInvoiceTerms extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceTerm> returnVal = new ArrayList<InvoiceTerm>();
try{
List<GenericValue> results = delegator.findAll("InvoiceTerm", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceTermMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceTermFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
