package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.category;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.category.GlAccountCategoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.category.GlAccountCategoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.category.GlAccountCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountCategory extends Command {

private GlAccountCategory elementToBeAdded;
public AddGlAccountCategory(GlAccountCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlAccountCategoryId(delegator.getNextSeqId("GlAccountCategory"));
GenericValue newValue = delegator.makeValue("GlAccountCategory", elementToBeAdded.mapAttributeField());
addedElement = GlAccountCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
