
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.item.InvoiceItemFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.item.InvoiceItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.item.InvoiceItem;


public class FindAllInvoiceItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItem> returnVal = new ArrayList<InvoiceItem>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
