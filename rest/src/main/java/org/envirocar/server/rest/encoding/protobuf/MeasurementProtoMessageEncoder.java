package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.GeometryOuterClass;
import org.enviroCar.server.rest.SensorOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.entities.Measurement;
import org.envirocar.server.core.entities.MeasurementValue;
import org.envirocar.server.core.entities.Sensor;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.core.util.GeoJSONConstants;
import org.envirocar.server.rest.Schemas;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.enviroCar.server.rest.MeasurementOuterClass;
import org.locationtech.jts.geom.Geometry;

@Provider
@Singleton
public class MeasurementProtoMessageEncoder extends AbstractProtoMessageEncoder<Measurement> {
    private final ProtoMessageEncoder<Geometry> geometryEncoder;
    private final ProtoMessageEncoder<Sensor> sensorEncoder;
    private final ProtoMessageEncoder<User>  userEncoder;

    @Inject
    MeasurementProtoMessageEncoder(ProtoMessageEncoder<Geometry> geometryEncoder,
                                   ProtoMessageEncoder<Sensor> sensorEncoder,
                                   ProtoMessageEncoder<User> userEncoder) {
        super(Measurement.class);
        this.geometryEncoder = geometryEncoder;
        this.sensorEncoder = sensorEncoder;
        this.userEncoder = userEncoder;
    }


    @Override
    public Message encode(Measurement entity, AccessRights rights, MediaType mediaType) {
        MeasurementOuterClass.Measurement.Builder measurementBuilder = MeasurementOuterClass.Measurement.newBuilder();
        measurementBuilder.setType(GeoJSONConstants.FEATURE_TYPE);
        if (entity.hasGeometry() && rights.canSeeGeometryOf(entity)) {
           measurementBuilder.setGeometry((GeometryOuterClass.Geometry)geometryEncoder.encode(entity.getGeometry(), rights, mediaType));
        }

        MeasurementOuterClass.Measurement.Properties.Builder mPropBuilder = MeasurementOuterClass.Measurement.Properties
                .newBuilder();

        if (entity.hasIdentifier()) {
            mPropBuilder.setId(entity.getIdentifier());
        }
        if (entity.hasTime() && rights.canSeeTimeOf(entity)) {
            mPropBuilder.setTime(getDateTimeFormat().print(entity.getTime()));
        }

        if (!getSchemaUriConfiguration().isSchema(mediaType, Schemas.TRACK)) {
            if (entity.hasSensor() && rights.canSeeSensorOf(entity)){
                mPropBuilder.setSensor((SensorOuterClass.Sensor) sensorEncoder.encode(entity.getSensor(), rights, mediaType));
            }
            if (entity.hasUser() && rights.canSeeUserOf(entity)) {
                mPropBuilder.setUser((UserOuterClass.User)userEncoder.encode(entity.getUser(), rights, mediaType));
            }
            if (entity.hasModificationTime() && rights.canSeeModificationTimeOf(entity)) {
                mPropBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
            }
            if (entity.hasCreationTime() && rights.canSeeCreationTimeOf(entity)) {
                mPropBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
            }
            if (entity.hasTrack() && rights.canSeeTracks()) {
                mPropBuilder.setTrack(entity.getTrack().getIdentifier());
            }
        }
        if (getSchemaUriConfiguration().isSchema(mediaType, Schemas.MEASUREMENT)
                || getSchemaUriConfiguration().isSchema(mediaType, Schemas.MEASUREMENTS)
                || getSchemaUriConfiguration().isSchema(mediaType, Schemas.TRACK)) {
            if (rights.canSeeValuesOf(entity)) {
                for (MeasurementValue measurementValue : entity.getValues()) {
                    MeasurementOuterClass.Measurement.Phenomenon.Builder phenoBuilder =
                            MeasurementOuterClass.Measurement.Phenomenon.newBuilder();
                    if(measurementValue.hasPhenomenon()) {
                        if (measurementValue.getPhenomenon().hasName()) {
                            phenoBuilder.setName(measurementValue.getPhenomenon().getName());
                        }
                        if (measurementValue.getPhenomenon().hasUnit()) {
                            phenoBuilder.setUnit(measurementValue.getPhenomenon().getUnit());
                        }
                    }
                    if(measurementValue.hasValue()) {
                        phenoBuilder.setValue(measurementValue.getValue().toString());
                    }
                }
            }
        }
        measurementBuilder.setProperties(mPropBuilder.build());
        return measurementBuilder.build();
    }
}
