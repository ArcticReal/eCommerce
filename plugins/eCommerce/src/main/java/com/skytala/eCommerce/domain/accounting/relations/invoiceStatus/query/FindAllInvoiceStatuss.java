
package com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.event.InvoiceStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.mapper.InvoiceStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.model.InvoiceStatus;


public class FindAllInvoiceStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceStatus> returnVal = new ArrayList<InvoiceStatus>();
try{
List<GenericValue> results = delegator.findAll("InvoiceStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
