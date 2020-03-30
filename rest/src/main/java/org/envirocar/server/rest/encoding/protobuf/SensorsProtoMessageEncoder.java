package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.SensorOuterClass;
import org.envirocar.server.core.entities.Sensor;
import org.envirocar.server.core.entities.Sensors;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class SensorsProtoMessageEncoder extends AbstractProtoMessageEncoder<Sensors> {
    private final ProtoMessageEncoder<Sensor> sensorEncoder;

    @Inject
    public SensorsProtoMessageEncoder(ProtoMessageEncoder<Sensor> sensorEncoder) {
        super(Sensors.class);
        this.sensorEncoder = sensorEncoder;
    }

    @Override
    public Message encode(Sensors entity, AccessRights rights, MediaType mediaType) {
        SensorOuterClass.SensorCollection.Builder sensorCollection = SensorOuterClass
                .SensorCollection.newBuilder();
        for (Sensor sensor : entity) {
            sensorCollection.addSensor((SensorOuterClass.Sensor)sensorEncoder.encode(sensor, rights, mediaType));
        }
        return sensorCollection.build();
    }
}
