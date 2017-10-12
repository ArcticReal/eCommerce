
package com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.event.InvoiceTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.mapper.InvoiceTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.model.InvoiceTypeAttr;


public class FindAllInvoiceTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceTypeAttr> returnVal = new ArrayList<InvoiceTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("InvoiceTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
