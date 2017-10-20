
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute.InvoiceItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAttribute.InvoiceItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;


public class FindAllInvoiceItemAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemAttribute> returnVal = new ArrayList<InvoiceItemAttribute>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
