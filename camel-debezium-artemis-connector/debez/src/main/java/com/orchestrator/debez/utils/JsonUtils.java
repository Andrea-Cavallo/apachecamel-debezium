package com.orchestrator.debez.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.orchestrator.debez.exception.InternalException;

public final class JsonUtils {
	
	private static ObjectMapper objectMapper;

	private JsonUtils() {
		throw new InternalException("Can't instantiate class JsonUtil");
	}

	public static ObjectMapper getMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.registerModule(new JavaTimeModule());
		}
		return objectMapper;
	}

	public static <T> T toObject(String json, Class<T> tClass) {
		try {
			return getMapper().readValue(json, tClass);
		} catch (IOException e) {
			throw new InternalException("Error during conversion from json to object", e);
		}
	}

	public static String toJson(Object object) {
		try {
			return getMapper().writeValueAsString(object);
		} catch (IOException e) {
			throw new InternalException("Error during conversion from object to json", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> arrayList(String json, Class<T> tClass) {
		try {
			TypeFactory typeFactory = getMapper().getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, tClass);
			return (List<T>) getMapper().readValue(json, (JavaType) collectionType);
		} catch (IOException e) {
			throw new InternalException("Error during conversion from Object to List", e);
		}
	}

	public static JsonNode jsonToTree(String json) {
		try {
			return getMapper().readTree(json);
		} catch (IOException e) {
			throw new InternalException("Error during conversion from json to JsonNode", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String json, Class<K> keyClass, Class<V> valueClass) {
		try {
			MapType type = getMapper().getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
			return (Map<K, V>) getMapper().readValue(json, (JavaType) type);
		} catch (IOException e) {
			throw new InternalException("Error during conversion from json to JsonNode", e);
		}
	}
}
