package com.clinic.clinic.common.utils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.clinic.clinic.common.consts.IConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;

public class JsonJava8TimeSerialization {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY);
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY_HHMM);
	
	public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
		  
	    @Override
	    public void serialize(LocalDate date, JsonGenerator generator,
	            SerializerProvider provider) throws IOException,
	            JsonProcessingException {
	 
	        String dateString = date.format(DATE_FORMATTER);
	        generator.writeString(dateString);
	    }
	}
	
	public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
		 
	    @Override
	    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
	            throws IOException, JsonProcessingException {
	 
	        ObjectCodec oc = jp.getCodec();
	        TextNode node = (TextNode) oc.readTree(jp);
	        String dateString = node.textValue();
	 
	        return LocalDate.parse(dateString, DATE_FORMATTER);
	    }
	}

	public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
		  
	    @Override
	    public void serialize(LocalDateTime date, JsonGenerator generator,
	            SerializerProvider provider) throws IOException,
	            JsonProcessingException {
	 
	        String dateString = date.format(DATETIME_FORMATTER);
	        generator.writeString(dateString);
	    }
	}
	
	public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
		 
	    @Override
	    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
	            throws IOException, JsonProcessingException {
	 
	        ObjectCodec oc = jp.getCodec();
	        TextNode node = (TextNode) oc.readTree(jp);
	        String dateString = node.textValue();
	 
	        return LocalDateTime.parse(dateString, DATETIME_FORMATTER);
	    }
	}
}