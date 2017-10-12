package com.skytala.eCommerce.domain.content.relations.surveyPage.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyPage.event.SurveyPageAdded;
import com.skytala.eCommerce.domain.content.relations.surveyPage.mapper.SurveyPageMapper;
import com.skytala.eCommerce.domain.content.relations.surveyPage.model.SurveyPage;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyPage extends Command {

private SurveyPage elementToBeAdded;
public AddSurveyPage(SurveyPage elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyPage addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyPageSeqId(delegator.getNextSeqId("SurveyPage"));
GenericValue newValue = delegator.makeValue("SurveyPage", elementToBeAdded.mapAttributeField());
addedElement = SurveyPageMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyPageAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
