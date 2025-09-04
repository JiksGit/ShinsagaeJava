package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//JWT 토큰을 생성, 검증하는 객체 
public class MyJwtUtil {
	
	private static final String SECRET="MySecret123"; //우리 앱에서 사용할 시크릿 키
	
	/*
	 데이터 전송을 위해서는 인코딩될 필요가있는데, 본 예제에서는 Base64기반 인코딩을 사용하겠음..
	 JWT 규격(스팩) Base64Url 인코딩을 사용해야 한다
	 [ Base64Url 인코딩의 특징 ] 
	 예를 들어 전송할 데이터 =(padding)과 같은 문자가 포함되어 있을 경우 이 패딩을 제거해 줌 , 즉 안전한 형태로
	 변환해줌..
	 예)  +는 -(대시)로 변환, /는 _(언더스코어)로 변환, =(패딩) 은 생략
	  	abcd+/==  는 Base64Url 로 인코딩하면, abcd-_
	  	따라서 URL 파라미터로 전송 시 Base64Url 로 인코딩하면 안전하게 전송이 됨 
	  	https://xxxx.com?token=abcd-_
	*/ 	
	private static String base64UrlEncode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
	
	/*
	HMAC-SHA256 알고리즘이란?  (Hash-based  Message Authentication Code)
	HMAC 방식에 기존 SHA256 해시 함수를 결합한 메시지 인증 코드 알고리즘임 
	data : Header(머리) + 	Payload(몸)
	secret: 서명에 사용할 비번
	JWT에 디지털 서명을 하기 위한 메서드임
	디지털 서명 시, Header와 Payload를 함께 사용하는 이유? 
	 - 서버가 발급한 원본 데이터임을 보장하기 위함 
	 - jwt 보유한 클라이언트가 임의로 payload의 내용을 바꿔치기 못하도록 하기 위함 
	*/
	private static String hmacSha256(String data, String secret) {
		Mac mac=null;
		try {
			mac=Mac.getInstance("HmacSHA256");//알고리즘 선택
			
			//비밀키 생성 
			SecretKeySpec secretKeySpec=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			mac.init(secretKeySpec); //비밀키 등록
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return base64UrlEncode(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
	}
	
	//클라이언트서버의 자원을 접근할때 사용할 엑세스용 토큰 생성 메서드 
	public static String generateAccessToken(String username, long expireMillis) {
		
		//Header 머리만들기		
		String headerJson="{ \"alg\":\"HS256\", \"typ\":\"JWT\"}"; //알고리즘은 HS256
		String header=base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
		
		//Payload 몸체만들기
		long exp = (System.currentTimeMillis() + expireMillis) /1000; //초 단위임 (밀리세컨드 아님)
		
		//자바도 서식 문자열(=포맷문자열)이 지원됨
		//%s 를 포맷 지정자(specifier)  %s 문자열로 대체됨 , %d 정수로 대체됨 
		String payloadJson = String.format("{\"sub\" : \"%s\", \"exp\": %d }",username, exp);
		String payload=base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));
		
		//Signature 서명하기 
		String data=header+"."+payload;
		
		//서명 = header+payload+우리앱비번
		String signature=hmacSha256(data, SECRET);
		
		//최종 결과물 반환 
		return data+"."+signature;   //JWT는 header.payload.signature 으로 구성됨.
	}
	
	//클라이언트에게 새로운 토큰을 발급할 리프레시 토큰 생성 메서드
	
	
	//JWT 검증
	//1.최종 서명결과와 클라이언트의 JWT가 같은지
	//2.JWT의 유효시간이 만료되었는지..
	public static boolean validateToken(String token) {
		boolean flag=true;
		
		String[] parts=token.split("\\.");  //.을 기준으로 배열로 반환..
		
		//배열의 수가 3이 아닌 경우는 검증할 필요조차 없는 올바르지 않은 JWT임 
		if(parts.length !=3)flag=false;
		
		String data=parts[0]+"."+parts[1];
		String expectedSig=hmacSha256(data, SECRET);
		
		if(!expectedSig.equals(parts[2])) {
			flag=false;
		}
		
		String payloadJson=new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
		
		//exp: 뒤의 숫자만 남기고 나머지는 전부 지우버리자(즉 시간만 추출하자)
		// . 임의의 한 문자또는 여러개 들... d는 숫자  +는 패턴이 1회 이상 반복되는 경우 (여러개의 숫자)
		//패턴이 발견된 결과 즉 매핑 결과를 그룹1에 저장
		//String expStr=payloadJson.replaceAll(".*\"exp\":(\\d+).*", "$1");
		//System.out.println("payloadJson "+payloadJson);
		//System.out.println("추출한 유효기간은 "+expStr);
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		try {
			JsonNode node=objectMapper.readTree(payloadJson);
			long exp=node.path("exp").asLong(); //밀리초이기 때문에..
			
			System.out.println("파싱 후 추출한 유효시간은 "+exp);
			
			//현재 시간이 유효기간보다 크다면, 유효기간은 지난것임 
			long now=System.currentTimeMillis()/1000;
			
			if(now > exp) {
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
		
	
	//JWT로부터 유저명 꺼내기 (자주 있는 일이므로, 아예 메서드로 지원해놓자) 
	public static String getUsername(String token) {
		String[] parts=token.split("\\.");  //.을 기준으로 배열로 반환..
		String payloadJson=new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
		ObjectMapper objectMapper=new ObjectMapper();
		
		String sub=null;
		
		try {
			JsonNode node=objectMapper.readTree(payloadJson);
			sub=node.path("sub").asText();
			System.out.println("추출한 유저 정보는 "+sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sub;	
	}
	
	
	public static void main(String[] args) {
		
	}
}










