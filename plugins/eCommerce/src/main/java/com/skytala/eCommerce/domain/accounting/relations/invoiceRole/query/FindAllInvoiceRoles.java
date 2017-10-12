
package com.skytala.eCommerce.domain.accounting.relations.invoiceRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.event.InvoiceRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.mapper.InvoiceRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceRole.model.InvoiceRole;


public class FindAllInvoiceRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceRole> returnVal = new ArrayList<InvoiceRole>();
try{
List<GenericValue> results = delegator.findAll("InvoiceRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
