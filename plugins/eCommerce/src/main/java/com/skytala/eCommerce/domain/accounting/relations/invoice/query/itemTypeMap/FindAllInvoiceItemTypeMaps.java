
package com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemTypeMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeMap.InvoiceItemTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;


public class FindAllInvoiceItemTypeMaps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<InvoiceItemTypeMap> returnVal = new ArrayList<InvoiceItemTypeMap>();
try{
List<GenericValue> results = delegator.findAll("InvoiceItemTypeMap", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(InvoiceItemTypeMapMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new InvoiceItemTypeMapFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
