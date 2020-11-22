package com.aipalpay.apr.api;

import com.aipalpay.apr.services.AprService;
import com.aipalpay.apr.services.AprServiceImpl;
import com.aipalpay.data.models.enums.SettingType;
import com.aipalpay.data.models.lambda.LambdaResponse;
import com.aipalpay.data.models.profile.Setting;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AprAPI implements RequestHandler<Setting, LambdaResponse> {

	static AprService svc;
	
	{
		svc = new AprServiceImpl();
	}
	
    @Override
    public LambdaResponse handleRequest(Setting input, Context context) {
    	AprServiceImpl.logger= context.getLogger();
    	AprServiceImpl.logger.log(" Setting data" + input.toString());
        
    	if(input.getType()!= null && input.getType() == SettingType.APR ) {
    		return svc.save(input);
    	}
    	
        LambdaResponse response = svc.get(input);
        
       return response;
    }
}