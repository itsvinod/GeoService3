package com.geodetails.application.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geodetails.application.model.ResponseResultModel;

@Service
public class GeoRepesponseService {

	// @Autowired
	// RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(GeoRepesponseService.class);

	@Value("${mockservice.url}")
	String mockserviceUrl;

	
	//@RequestMapping(method = RequestMethod.GET)
	public Callable<String> getGeoDetails(String ip) throws InterruptedException {
	    return new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	        	RestTemplate restTemplate1 = new RestTemplate();
	    		//String input = "{\"ip\":\"192.168.1.2\"}";
	    		String resMsg = null;

	    		HttpHeaders headers = new HttpHeaders();
	    		headers.setContentType(MediaType.APPLICATION_JSON);
	    		HttpEntity<String> entity = new HttpEntity<String>(ip, headers);

	    		// ResponseEntity<String> response = restTemplate1.exchange(uri,
	    		// HttpMethod.POST, entity, String.class);
	    		Map<String, String> inpt = new HashMap<String, String>();
	    		inpt.put("ip", ip);
	    		logger.info(" ip={}....", ip);
	    		logger.info(" mockserviceUrl={}....", mockserviceUrl);
	    		//To be removed
	    		try {
	    			if(!"192.168.1.2".equals(ip)) {
	    				Thread.sleep(6000);
	    			}
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		
	    		ResponseEntity<ResponseResultModel> resEnty = restTemplate1.postForEntity(mockserviceUrl, inpt,
	    				ResponseResultModel.class);
	    		ResponseResultModel reMdl = resEnty.getBody();
	    		logger.info("after post2 res=" + reMdl.getResults());
	    		String addrs[] = null;
	    		if (reMdl.getResults() != null && reMdl.getResults().length > 0) {
	    			if (reMdl.getResults()[0].getFormattedAddressLines() != null
	    					&& reMdl.getResults()[0].getFormattedAddressLines().length > 2) {
	    				addrs = reMdl.getResults()[0].getFormattedAddressLines();
	    				resMsg = addrs[0] + "," + addrs[1] + "," + addrs[2] + ",";
	    			} else {
	    				resMsg = "No Full Data Found";
	    			}
	    		} else {
	    			resMsg = "No Data Found.";
	    		}
	    		logger.info("resMsg={}", resMsg);
	    		return resMsg;
	        }
	    };
	}
	
	/*
	public Callable<String> getGeoDetails() {
		return new Callable<String>() throws InterruptedException{
		
		RestTemplate restTemplate1 = new RestTemplate();
		String input = "{\"ip\":\"192.168.1.2\"}";
		String resMsg = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);

		// ResponseEntity<String> response = restTemplate1.exchange(uri,
		// HttpMethod.POST, entity, String.class);
		Map<String, String> inpt = new HashMap<String, String>();
		inpt.put("ip", "192.168.1.2");
		logger.info("** mockserviceUrl={}....", mockserviceUrl);
		//To be removed
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<ResponseResultModel> resEnty = restTemplate1.postForEntity(mockserviceUrl, inpt,
				ResponseResultModel.class);
		ResponseResultModel reMdl = resEnty.getBody();
		logger.info("after post2 res=" + reMdl.getResults());
		String addrs[] = null;
		if (reMdl.getResults() != null && reMdl.getResults().length > 0) {
			if (reMdl.getResults()[0].getFormattedAddressLines() != null
					&& reMdl.getResults()[0].getFormattedAddressLines().length > 2) {
				addrs = reMdl.getResults()[0].getFormattedAddressLines();
				resMsg = addrs[0] + "," + addrs[1] + "," + addrs[2] + ",";
			} else {
				resMsg = "No Full Data Found";
			}
		} else {
			resMsg = "No Data Found.";
		}
		logger.info("resMsg={}", resMsg);
		return resMsg;
	}
	};
*/
}