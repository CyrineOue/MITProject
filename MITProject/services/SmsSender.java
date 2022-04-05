package tn.MITProject.services;

import tn.MITProject.services.SmsRequest;

public interface SmsSender {
	
	void sendSms(SmsRequest smsRequest,Long idproduct);

}
