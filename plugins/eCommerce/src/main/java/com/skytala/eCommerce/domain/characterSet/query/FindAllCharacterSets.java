
package com.skytala.eCommerce.domain.characterSet.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.characterSet.event.CharacterSetFound;
import com.skytala.eCommerce.domain.characterSet.mapper.CharacterSetMapper;
import com.skytala.eCommerce.domain.characterSet.model.CharacterSet;


public class FindAllCharacterSets extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CharacterSet> returnVal = new ArrayList<CharacterSet>();
try{
List<GenericValue> results = delegator.findAll("CharacterSet", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CharacterSetMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CharacterSetFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
