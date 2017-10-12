
package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.event.ZipSalesRuleLookupFound;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.mapper.ZipSalesRuleLookupMapper;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model.ZipSalesRuleLookup;


public class FindAllZipSalesRuleLookups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ZipSalesRuleLookup> returnVal = new ArrayList<ZipSalesRuleLookup>();
try{
List<GenericValue> results = delegator.findAll("ZipSalesRuleLookup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ZipSalesRuleLookupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ZipSalesRuleLookupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
