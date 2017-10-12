
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.event.FixedAssetMaintFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.mapper.FixedAssetMaintMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaint.model.FixedAssetMaint;


public class FindAllFixedAssetMaints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetMaint> returnVal = new ArrayList<FixedAssetMaint>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetMaint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetMaintMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetMaintFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
