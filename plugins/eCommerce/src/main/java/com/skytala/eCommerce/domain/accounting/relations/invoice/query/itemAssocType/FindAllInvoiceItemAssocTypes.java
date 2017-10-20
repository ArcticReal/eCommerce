
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAssocType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssocType.InvoiceItemAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType.InvoiceItemAssocType;


public class FindAllInvoiceItemAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemAssocType> returnVal = new ArrayList<InvoiceItemAssocType>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
