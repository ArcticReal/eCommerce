
package com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.event.FixedAssetIdentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.mapper.FixedAssetIdentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.model.FixedAssetIdent;


public class FindAllFixedAssetIdents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetIdent> returnVal = new ArrayList<FixedAssetIdent>();
try{
List<GenericValue> results = delegator.findAll("FixedAssetIdent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FixedAssetIdentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FixedAssetIdentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
