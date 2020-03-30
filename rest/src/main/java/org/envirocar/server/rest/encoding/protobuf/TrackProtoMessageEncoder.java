package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.MeasurementOuterClass;
import org.enviroCar.server.rest.SensorOuterClass;
import org.enviroCar.server.rest.TrackOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.DataService;
import org.envirocar.server.core.entities.*;
import org.envirocar.server.core.filter.MeasurementFilter;
import org.envirocar.server.core.util.GeoJSONConstants;
import org.envirocar.server.rest.Schemas;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class TrackProtoMessageEncoder extends AbstractProtoMessageEncoder<Track> {
    private final ProtoMessageEncoder<Sensor> sensorEncoder;
    private final ProtoMessageEncoder<User> userEncoder;
    private final ProtoMessageEncoder<Measurements> measurementsEncoder;
    private final DataService dataService;

    @Inject
    TrackProtoMessageEncoder(ProtoMessageEncoder<Sensor> sensorEncoder,
                             ProtoMessageEncoder<User> userEncoder,
                             ProtoMessageEncoder<Measurements> measurementsEncoder,
                             DataService dataService) {
        super(Track.class);
        this.sensorEncoder = sensorEncoder;
        this.userEncoder = userEncoder;
        this.measurementsEncoder = measurementsEncoder;
        this.dataService = dataService;
    }
    @Override
    public Message encode(Track entity, AccessRights rights, MediaType mediaType) {
        TrackOuterClass.Track.Builder trackBuilder = TrackOuterClass.Track.newBuilder();

        if (getSchemaUriConfiguration().isSchema(mediaType, Schemas.TRACK)) {
            trackBuilder.setType(GeoJSONConstants.FEATURE_COLLECTION_TYPE);
            if (entity.hasIdentifier()) {
                trackBuilder.setId(entity.getIdentifier());
            }
            if (entity.hasName() && rights.canSeeNameOf(entity)) {
                trackBuilder.setName(entity.getName());
            }
            if (entity.hasDescription() && rights.canSeeDescriptionOf(entity)) {
                trackBuilder.setDescription(entity.getDescription());
            }
            if (entity.hasCreationTime() && rights.canSeeCreationTimeOf(entity)) {
                trackBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
            }
            if (entity.hasModificationTime() && rights.canSeeModificationTimeOf(entity)) {
                trackBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
            }
            if (entity.hasSensor() && rights.canSeeSensorOf(entity)) {
                trackBuilder.setSensor((SensorOuterClass.Sensor)sensorEncoder.encode(entity.getSensor(), rights, mediaType));
            }
            if (entity.hasUser() && rights.canSeeUserOf(entity)) {
                trackBuilder.setUser((UserOuterClass.User)userEncoder.encode(entity.getUser(), rights, mediaType));
            }
            if (rights.canSeeLengthOf(entity)) {
                trackBuilder.setLength(entity.getLength());
            }
            if (entity.hasAppVersion() && rights.canSeeAppVersionOf(entity)) {
                trackBuilder.setAppVersion(entity.getAppVersion());
            }
            addStartAndEnd(entity, rights, trackBuilder);
            if (entity.hasTouVersion() && rights.canSeeTouVersionOf(entity)) {
                trackBuilder.setTouVersion(entity.getTouVersion());
            }
            if (rights.canSeeMeasurementsOf(entity)) {
                Measurements values = dataService.getMeasurements(new MeasurementFilter(entity));
                trackBuilder.setMeasurements((MeasurementOuterClass.MeasurementCollection) measurementsEncoder
                        .encode(values, rights, mediaType));
            }
        } else {
            if (entity.hasIdentifier()) {
                trackBuilder.setId(entity.getIdentifier());
            }
            if (entity.hasModificationTime() && rights.canSeeModificationTimeOf(entity)) {
                trackBuilder.setModified(getDateTimeFormat().print((entity.getModificationTime())));
            }
            if (entity.hasName() && rights.canSeeNameOf(entity)) {
                trackBuilder.setName(entity.getName());
            }

            //additional props
            if (entity.hasLength() && rights.canSeeLengthOf(entity)) {
                trackBuilder.setLength(entity.getLength());
            }
            addStartAndEnd(entity, rights, trackBuilder);
            if (entity.hasSensor() && rights.canSeeSensorOf(entity)) {
                trackBuilder.setSensor((SensorOuterClass.Sensor)sensorEncoder.encode(entity.getSensor(), rights, mediaType));
            }
        }
        return trackBuilder.build();
    }

    private void addStartAndEnd(Track entity, AccessRights rights, TrackOuterClass.Track.Builder trackBuilder) {
        if (entity.hasBegin()) {
            trackBuilder.setBegin(getDateTimeFormat().print(entity.getBegin()));
        }
        if (entity.hasEnd()) {
            trackBuilder.setEnd(getDateTimeFormat().print(entity.getEnd()));
        }
    }
}
