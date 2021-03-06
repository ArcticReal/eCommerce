package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgAdded implements Event{

	private PaymentGatewayRespMsg addedPaymentGatewayRespMsg;
	private boolean success;

	public PaymentGatewayRespMsgAdded(PaymentGatewayRespMsg addedPaymentGatewayRespMsg, boolean success){
		this.addedPaymentGatewayRespMsg = addedPaymentGatewayRespMsg;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayRespMsg getAddedPaymentGatewayRespMsg() {
		return addedPaymentGatewayRespMsg;
	}

}
