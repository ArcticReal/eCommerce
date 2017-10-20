
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.termAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.termAttribute.InvoiceTermAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.termAttribute.InvoiceTermAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.termAttribute.InvoiceTermAttribute;


public class FindAllInvoiceTermAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceTermAttribute> returnVal = new ArrayList<InvoiceTermAttribute>();
try{
List<GenericValue> results = delegator.findAll("InvoiceTermAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceTermAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceTermAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
