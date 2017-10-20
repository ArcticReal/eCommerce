
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.attribute.InvoiceAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;


public class FindAllInvoiceAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceAttribute> returnVal = new ArrayList<InvoiceAttribute>();
try{
List<GenericValue> results = delegator.findAll("InvoiceAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
