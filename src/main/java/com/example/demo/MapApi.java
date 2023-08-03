package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapApi {
	public static void main(String[] args) {
		
	}

	public static String getAddress(String latitude, String longitude) {
		JsonReader jsonReader = new JsonReader();
		// api 키값
		String key = "1DD1C05D-1BCE-31E7-BCA4-2CF4C6A3CA86";
		// # 파라미터 종류 확인 : http://www.vworld.kr/dev/v4dv_geocoderguide2_s002.do
		String reverseGeocodeURL = "http://api.vworld.kr/req/address?"
				+ "service=address&request=getAddress&version=2.0&crs=epsg:4326&point="
				+  longitude + "," +  latitude
				+ "&format=json"
				+ "&type=both&zipcode=true"
				+ "&simple=false&"
				+ "key="+key;
		String getJson = jsonReader.callURL(reverseGeocodeURL);
		Map<String, Object> map = jsonReader.string2Map(getJson);

		// 지도 결과 확인하기
		ArrayList reverseGeocodeResultArr = (ArrayList) ((HashMap<String, Object>) map.get("response")).get("result");

		String level4L = "";

		for (int counter = 0; counter < reverseGeocodeResultArr.size(); counter++) {
			HashMap<String, Object> tmp = (HashMap<String, Object>) reverseGeocodeResultArr.get(counter);
			level4L = (String) ((HashMap<String, Object>) tmp.get("structure")).get("level4L");
			
		}

		return level4L;
	}
	
}
