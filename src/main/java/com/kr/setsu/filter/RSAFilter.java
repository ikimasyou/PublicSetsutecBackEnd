package com.kr.setsu.filter;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;


import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.math3.util.ContinuedFraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.setsu.entity.PublicKeyEntity;
import com.kr.setsu.service.serviceImpl.UserServiceImpl;



@Scope("singleton")
@Component
public class RSAFilter implements Filter{
	
	// 该HashMap中的Key为公钥，Value为公私钥实体，包含了服务器公私钥和客户端公钥。
	public static ConcurrentHashMap<String,PublicKeyEntity> allKeys = new ConcurrentHashMap<>();
	
	// 为了解密代码方便，该HashMap中的Key仍为公钥，Value为服务器对应的私钥对象，该对象可直接传给Cipher用于解密。
	public static ConcurrentHashMap<String,PrivateKey> keyPairs = new ConcurrentHashMap<>();
	
	//
	public static ConcurrentHashMap<String,Date> expiredChecker = new ConcurrentHashMap<String, Date>();
	
	//原本是UserServiceImpl.class，被我更改为了RSAFilter.class
	private Logger logger = LoggerFactory.getLogger(RSAFilter.class);
	
	
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		//logger.info("*******************************");
		logger.info("请求被RSAFilter拦截");

		// RequestWrapper的存在意义：如果想从原始的servletRequest中提取body，会有两个问题：
		// 1、无法再解密之后将解析后的body放入servletRequest
		// 2、由于读取body需要用到servletRequst中的字节流，而字节流只能被读取一次，因此在Filter层读取之后，Controller层在解析Json将无法再读取body，导致后续的业务逻辑无法执行。
		HttpServletRequest hr=(HttpServletRequest) servletRequest;
		RequestWrapper wr = new RequestWrapper(hr);
		
		// 在前端进行POST请求时，如果涉及到跨域，则在POST请求之前会发送一个OPTIONS请求来验证跨域，一个请求想要到达Controller要在SpringBoot中经过Filter层->Servlet层->Inteceptor层->Controller层
		// 在实际代码应用中发现，跨域检查会在此Filter之后进行，因此需要将OPTIONS请求放行，待前后端利用OPTIONS请求进行跨域检查之后，再进行对POST请求的处理。
		//将"OPTIONS".equals(wr.getMethod())改为了
		if(HttpMethod.OPTIONS.matches(wr.getMethod())) {
			logger.info("Filter处理结束");
			//logger.info("*******************************");
			chain.doFilter(servletRequest, servletResponse);
		}
		else {
			ServletRequest request = null;

			 //使用RequestContextHolder获取当前请求
			if (servletRequest instanceof HttpServletRequest) {
	            
	            //该String为接受到的HTTP请求的Body
	            String requestBodyStr = wr.getBody();
	            logger.info("服务器端收到的原始请求（Request Body）:"+requestBodyStr);
	            //logger.info();
	
	            String privateKeyString = null;
	            String encryptedString = null;
	            
	            try {
	                // 使用 ObjectMapper 将字符串转换为 JsonNode 对象
	                ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode jsonNode = objectMapper.readTree(requestBodyStr);
	
	                // 访问 JSON 对象的属性
	                privateKeyString = jsonNode.get("clientPublicKey").asText();
	                //System.out.println("privateKeyString:"+privateKeyString);
	
	                encryptedString = jsonNode.get("token").asText().trim();
	                // System.out.println("encryptedString:"+encryptedString);
	                // 输出解析结果
	
	            } catch (Exception e) {
	            	logger.error("解析JSON对象失败",e);
	                e.printStackTrace();
	            }
	//            
	//            
	//            
	//            
	            
	            //System.out.println(allKeys.size());
	//            
	           RSAPrivateKey  kp = (RSAPrivateKey)keyPairs.get(privateKeyString);
	
	            try {
	                // 使用私钥进行解密
	
	            	Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
	            	
	
	            	cipher.init(Cipher.DECRYPT_MODE, kp);
	
	
	                // 将 Base64 编码的字符串解码为字节数组
	                byte[] encryptedData = Base64.getDecoder().decode(encryptedString);
	
	                byte[] decryptedBytes = cipher.doFinal(encryptedData);
	
	                // 解密后的数据
	                String decryptedData = new String(decryptedBytes);
	                
	                //decryptedData = decryptedData.substring(1,decryptedData.length()-1);
	                String replacedString = decryptedData.replaceAll("\\\\\\\"", "\"");
	                logger.info("解密后的数据: " + replacedString);
	                wr.setBody(replacedString);
	            } catch (Exception e) {
	            	logger.error("RSA解密失败",e);
	                e.printStackTrace();
	            }
	//            
	
	            request = wr;
	        }
			if (request == null) {
				logger.info("Filter处理结束");
				//logger.info("*******************************");
	            chain.doFilter(servletRequest, servletResponse);
	           
	        } else {
	        	logger.info("Filter处理结束");
	        	//logger.info("*******************************");
	            chain.doFilter(request, servletResponse);
	        }
		}
        


		
        	
		
		// 这里原本是想用getPostData方法获取请求的data部分，但是会报错java.lang.IllegalStateException: getReader() has already been called for this request.因此换成这个方法。
//		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
//		Map<String, String[]> map = wrappedRequest.getParameterMap();
//		//map.forEach(s);
//		System.out.println(map);
//
//        // 获取请求体
//        // String postData = new String();
//		wrappedRequest.s
//		request.setAttribute("myusername", "muradil");
//		byte[] buffer = wrappedRequest.getContentAsByteArray();
//        System.out.println("postData("+buffer.length+")");
//        //System.out.println(postData);
		
//		String method =  httpRequest.getMethod();
//		if("POST".equalsIgnoreCase(method)) {
//			String postData = getPostData(httpRequest);
//            System.out.println("POST Data: " + postData);
//		}
		

		
		
		// doFilter方法用于在该Filter结束之后将请求还给Servlet继续做之后的逻辑

		// TODO Auto-generated method stub
		
	}
	
	private boolean isExpired(String publicKey) {
		
		
		return false;
	}
	

	
	
	
}

