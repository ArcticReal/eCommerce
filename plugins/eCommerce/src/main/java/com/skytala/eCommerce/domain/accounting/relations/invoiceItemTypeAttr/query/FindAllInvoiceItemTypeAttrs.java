
package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.event.InvoiceItemTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.mapper.InvoiceItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;


public class FindAllInvoiceItemTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemTypeAttr> returnVal = new ArrayList<InvoiceItemTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
