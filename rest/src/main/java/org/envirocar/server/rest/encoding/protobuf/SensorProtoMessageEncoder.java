package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.SensorOuterClass;
import org.envirocar.server.core.entities.Sensor;
import org.envirocar.server.rest.JSONConstants;
import org.envirocar.server.rest.Schemas;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
@Singleton
public class SensorProtoMessageEncoder extends AbstractProtoMessageEncoder<Sensor> {

    public SensorProtoMessageEncoder() {
        super(Sensor.class);
    }

    @Override
    public Message encode(Sensor entity, AccessRights rights, MediaType mediaType) {
        SensorOuterClass.Sensor.Builder sensorBuilder = SensorOuterClass.Sensor.newBuilder();
        if (entity.hasType()) {
            sensorBuilder.setType(entity.getType());
        }

        if (entity.hasProperties()) {
            Map<String, Object> properties = entity.getProperties();
            Object obj = null;
            for(String key : properties.keySet()) {
                sensorBuilder.addProperties(SensorOuterClass.Sensor.Property.newBuilder()
                        .setKey(key)
                        .setValue(properties.get(key).toString())
                        .build());
            }
        }
        sensorBuilder.addProperties(SensorOuterClass.Sensor.Property.newBuilder()
                        .setKey(JSONConstants.IDENTIFIER_KEY)
                        .setValue(entity.getIdentifier())
                        .build());
        if (getSchemaUriConfiguration().isSchema(mediaType, Schemas.SENSOR)) {
            if (entity.hasCreationTime()) {
                sensorBuilder.addProperties(SensorOuterClass.Sensor.Property.newBuilder()
                        .setKey(JSONConstants.CREATED_KEY)
                        .setValue(getDateTimeFormat().print(entity.getCreationTime()))
                        .build());
            }
            if (entity.hasModificationTime()) {
                sensorBuilder.addProperties(SensorOuterClass.Sensor.Property.newBuilder()
                        .setKey(JSONConstants.MODIFIED_KEY)
                        .setValue(getDateTimeFormat().print(entity.getModificationTime()))
                        .build());
            }
        }
        return sensorBuilder.build();
    }
}
