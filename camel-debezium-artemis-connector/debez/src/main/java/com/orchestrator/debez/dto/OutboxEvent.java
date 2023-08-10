package com.orchestrator.debez.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "aggregate_id")
	private Long aggregateId;

	@JsonProperty(value =  "aggregate_type")
	private String aggregateType;

	@JsonProperty(value = "event_type")
	private String eventType;

	@JsonProperty(value =  "version")
	private Integer version;

	@JsonProperty(value = "payload")
	private String payload;

	@JsonProperty(value = "published")
	private Boolean published;

	@JsonProperty(value = "created_at")
	private Instant createdAt;
}