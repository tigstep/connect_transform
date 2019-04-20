package org.tigstep.connect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.transforms.util.SimpleConfig;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.data.SchemaBuilder;

public class Transform {
	
	public static final ConfigDef CONFIG_DEF = new ConfigDef().define(
			"name"
			, ConfigDef.Type.STRING
			, ConfigDef.Importance.MEDIUM
			, "documentation_name"
			);	
	public static Schema keySchema = SchemaBuilder.struct()
			.name("key")
			.field("key_1", Schema.STRING_SCHEMA)
			.field("key_2", Schema.INT8_SCHEMA)
			.field("key_3", Schema.INT16_SCHEMA)
			.build();
	public static Schema valueSchema = SchemaBuilder.struct()
			.name("value")
			.field("key_1", Schema.STRING_SCHEMA)
			.field("key_2", Schema.INT8_SCHEMA)
			.field("key_3", Schema.INT16_SCHEMA)
			.build();
	public static Map<Integer, String> mymap = new HashMap<Integer, String>() {
		{
			put(1, "one");
			put(2, "two");
		}
	};
	public static SinkRecord record = new SinkRecord(
			"test_topic"
			, 0
			, keySchema
			, mymap
			, valueSchema
			, mymap
			,0
	);

	public static Schema operatingSchemaKey(SinkRecord record) {
		return record.keySchema();
	}

	public static Object operatingValueKey(SinkRecord record) {
		return record.key();
	}

	public static Schema operatingSchemaValue(SinkRecord record) {
		return record.valueSchema();
	}

	public static Object operatingValueValue(SinkRecord record) {
		return record.value();
	}
	public static void main(String[] args){
		Map<String,String> props = new HashMap<>();
		props.put("name", "test_name");
		final SimpleConfig config = new SimpleConfig(CONFIG_DEF,props);
		System.out.println(operatingSchemaKey(record));
		System.out.println(operatingValueKey(record));
		System.out.println(operatingSchemaValue(record));
		System.out.println(operatingValueValue(record));
		System.out.println(record.value().getClass());
	}
}
