package com.aipalpay.apr.services;

import com.aipalpay.data.models.lambda.LambdaResponse;
import com.aipalpay.data.models.profile.Setting;

public interface AprService {
	
	LambdaResponse save(Setting setting);
	LambdaResponse get(Setting setting);

}
