package com.skytala.eCommerce.domain.content.relations.survey.command.questionOption;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionAdded;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionOption.SurveyQuestionOptionMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyQuestionOption extends Command {

private SurveyQuestionOption elementToBeAdded;
public AddSurveyQuestionOption(SurveyQuestionOption elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyQuestionOption addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyOptionSeqId(delegator.getNextSeqId("SurveyQuestionOption"));
GenericValue newValue = delegator.makeValue("SurveyQuestionOption", elementToBeAdded.mapAttributeField());
addedElement = SurveyQuestionOptionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyQuestionOptionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
