package com.bysssss.goinmul.api.common.util;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	public static String toJson(Object obj) {
		if( obj==null) {
			return null;
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		
		return json;
	}
	
	public static <T> T toObject(String json, Class<T> classOfT) {
		if( json==null || classOfT==null) {
			return null;
		}
		
		Gson gson = new Gson();
		T templet = null;
		try {
			templet = gson.fromJson(json, classOfT);
		} catch (JsonSyntaxException e) {
			return null;
		}
		
		return templet;
	}
	
	public static JsonObject toJsonObject(String json) {
		//JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(json);
		
		return jsonElement.getAsJsonObject();
	}
	
	public static JsonArray toJsonArray(String json) {
		// JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(json);
		
		return jsonElement.getAsJsonArray();
	}

	public static <T> List<T> jsonToList(String s, Class<T[]> cls) {
		T[] arr = new Gson().fromJson(s, cls);
		return Arrays.asList(arr);
	}

	public static <T> JsonArray listToJsonArray(List<T> list) {
		JsonArray jsonArray = new Gson().toJsonTree(list, new TypeToken<List<T>>() {}.getType()).getAsJsonArray();
		return jsonArray;
	}
	
	public static JSONObject toJSONObject(String json) {
		if( json==null) {
			return null;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)jsonParser.parse(json);
		} catch (ParseException e) {
			return null;
		}
		
		return jsonObject;
	}
	
	public static JSONArray toJSONArray(String json) {
		if( json==null) {
			return null;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray)jsonParser.parse(json);
		} catch (ParseException e) {
			return null;
		}
		
		return jsonArray;
	}
}
