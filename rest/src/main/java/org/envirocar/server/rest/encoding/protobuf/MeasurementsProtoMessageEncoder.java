package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.MeasurementOuterClass;
import org.envirocar.server.core.entities.Measurement;
import org.envirocar.server.core.entities.Measurements;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class MeasurementsProtoMessageEncoder extends AbstractProtoMessageEncoder<Measurements> {
    private final ProtoMessageEncoder<Measurement> measurementEncoder;

    @Inject
    public MeasurementsProtoMessageEncoder(ProtoMessageEncoder<Measurement> measurementEncoder) {
        super(Measurements.class);
        this.measurementEncoder = measurementEncoder;
    }

    @Override
    public Message encode(Measurements entity, AccessRights rights, MediaType mediaType) {
        MeasurementOuterClass.MeasurementCollection.Builder measurementCollection = MeasurementOuterClass
                .MeasurementCollection.newBuilder();
        for (Measurement measurement : entity) {
            measurementCollection.addMeasurement((MeasurementOuterClass.Measurement)measurementEncoder.encode(measurement, rights, mediaType));
        }
        return measurementCollection.build();
    }
}
