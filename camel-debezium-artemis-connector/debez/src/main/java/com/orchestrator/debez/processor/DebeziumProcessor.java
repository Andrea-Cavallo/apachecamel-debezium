package com.orchestrator.debez.processor;
import java.util.HashMap;
import java.util.Map;
import static com.orchestrator.debez.utils.JsonUtils.toJson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Component;


@Component
public class DebeziumProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

		var struct = exchange.getMessage().getBody(Struct.class);
        Map<String,Object> value = structToMap(struct);
        String outboxEventAsJson = toJson(value);
        exchange.getIn().setBody(outboxEventAsJson);

    }
    
	private Map<String, Object> structToMap(Struct struct) {
		Map<String, Object> map = new HashMap<>();
		for (Field field : struct.schema().fields()) {
			Object fieldValue = struct.get(field);
			map.put(field.name(), fieldValue);
		}
		return map;
	}
}
