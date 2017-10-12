
package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.event.InvoiceItemTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.mapper.InvoiceItemTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.model.InvoiceItemTypeGlAccount;


public class FindAllInvoiceItemTypeGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemTypeGlAccount> returnVal = new ArrayList<InvoiceItemTypeGlAccount>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemTypeGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemTypeGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemTypeGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
