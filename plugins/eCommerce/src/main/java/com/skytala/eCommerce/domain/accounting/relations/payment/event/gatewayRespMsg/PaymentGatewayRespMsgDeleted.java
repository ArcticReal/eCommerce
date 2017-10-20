package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgDeleted implements Event{

	private boolean success;

	public PaymentGatewayRespMsgDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
