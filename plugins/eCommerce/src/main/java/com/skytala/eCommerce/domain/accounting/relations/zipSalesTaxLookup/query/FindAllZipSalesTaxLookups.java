
package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.event.ZipSalesTaxLookupFound;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.mapper.ZipSalesTaxLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model.ZipSalesTaxLookup;


public class FindAllZipSalesTaxLookups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ZipSalesTaxLookup> returnVal = new ArrayList<ZipSalesTaxLookup>();
try{
List<GenericValue> results = delegator.findAll("ZipSalesTaxLookup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ZipSalesTaxLookupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ZipSalesTaxLookupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
