
package com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.event.InvoiceItemTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.mapper.InvoiceItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemType.model.InvoiceItemType;


public class FindAllInvoiceItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemType> returnVal = new ArrayList<InvoiceItemType>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
