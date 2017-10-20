
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.contentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contentType.InvoiceContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;


public class FindAllInvoiceContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceContentType> returnVal = new ArrayList<InvoiceContentType>();
try{
List<GenericValue> results = delegator.findAll("InvoiceContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
