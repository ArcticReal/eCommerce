
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.type.InvoiceTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.type.InvoiceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.type.InvoiceType;


public class FindAllInvoiceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceType> returnVal = new ArrayList<InvoiceType>();
try{
List<GenericValue> results = delegator.findAll("InvoiceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
