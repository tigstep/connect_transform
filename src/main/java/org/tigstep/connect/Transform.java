package org.tigstep.connect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.transforms.util.SimpleConfig;
import org.apache.kafka.connect.sink.SinkRecord;

public class Transform {
	
	public static final ConfigDef CONFIG_DEF = new ConfigDef().define(
			"name"
			, ConfigDef.Type.STRING
			, ConfigDef.Importance.MEDIUM
			, "documentation_name"
			);	
	
	public static SinkRecord record = new SinkRecord("test_topic", 0, null, Collections.singletonMap("key", 42), null,  null, 0);
	
	public static void main(String[] args){
		Map<String,String> props = new HashMap<>();
		props.put("name", "test_name");
		final SimpleConfig config = new SimpleConfig(CONFIG_DEF,props);
		System.out.println(config.originals());
		System.out.println(record.toString());
	}
}
