
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.content;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.content.InvoiceContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;


public class FindAllInvoiceContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceContent> returnVal = new ArrayList<InvoiceContent>();
try{
List<GenericValue> results = delegator.findAll("InvoiceContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
