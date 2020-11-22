package com.aipalpay.apr.services;

import com.aipalpay.azure.services.configs.AzureAccountManager;
import com.aipalpay.azure.services.entities.SettingEntity;
import com.aipalpay.azure.services.interfaces.TableService;
import com.aipalpay.common.utils.CommonUtil;
import com.aipalpay.data.models.enums.ErrorCode;
import com.aipalpay.data.models.enums.SettingType;
import com.aipalpay.data.models.lambda.LambdaResponse;
import com.aipalpay.data.models.profile.Setting;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class AprServiceImpl implements AprService  {

	private static TableService platformAzureService = null;
	public static LambdaLogger logger;
	

	
	{
		platformAzureService = AzureAccountManager.getAzurePlatormService();
	}



	@Override
	public LambdaResponse save(Setting setting) {		

		LambdaResponse response = new LambdaResponse();
		try {
		setting.setType(SettingType.APR);
		SettingEntity entity = new SettingEntity(setting);
		response.setBody("Saved successfully.");
		response.setStatusCode(200);
		platformAzureService.insertSetting(entity);		
		}catch(Exception e) {
			e.printStackTrace();
			response.setBody(e.getMessage());
			response.setStatusCode(500);
			
		}
		
		return response;
	}



	@Override
	public LambdaResponse get(Setting setting) {
			LambdaResponse response = new LambdaResponse();
			
			try{
				Setting retSetting = platformAzureService.getSetting(SettingType.APR.toString(), setting.getAid());
			
			response.setBody(CommonUtil.getGSon().toJson(retSetting));
			response.setStatusCode(200);
			}catch (Exception e) {
				response.setBody("Failed to get data from setting");
				response.setStatusCode(400);
			}
			return response;
			
	}

	
}
