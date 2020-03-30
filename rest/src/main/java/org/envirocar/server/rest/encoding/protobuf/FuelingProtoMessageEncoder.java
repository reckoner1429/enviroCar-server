package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.FuelingOuterClass;
import org.enviroCar.server.rest.SensorOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.entities.DimensionedNumber;
import org.envirocar.server.core.entities.Fueling;
import org.envirocar.server.core.entities.Sensor;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import static com.google.common.base.Preconditions.checkNotNull;

@Provider
@Singleton
public class FuelingProtoMessageEncoder extends AbstractProtoMessageEncoder<Fueling> {

    private final ProtoMessageEncoder<User> userEncoder;
    private final ProtoMessageEncoder<Sensor> sensorEncoder;

    @Inject
    public FuelingProtoMessageEncoder(ProtoMessageEncoder<User> userEncoder,
                                      ProtoMessageEncoder<Sensor> sensorEncoder) {
        super(Fueling.class);
        this.userEncoder = checkNotNull(userEncoder);
        this.sensorEncoder = checkNotNull(sensorEncoder);
    }

    @Override
    public Message encode(Fueling entity, AccessRights rights, MediaType mediaType) {
        FuelingOuterClass.Fueling.Builder fuelingBuilder = FuelingOuterClass.Fueling.newBuilder();

        if (entity.hasFuelType()) {
            fuelingBuilder.setType(entity.getFuelType());
        }
        if (entity.hasCost()) {
            fuelingBuilder.setCost(encode(entity.getCost()));
        }
        if (entity.hasMileage()) {
            fuelingBuilder.setMileage(encode(entity.getMileage()));
        }
        if (entity.hasVolume()) {
            fuelingBuilder.setVolume(encode(entity.getVolume()));
        }
        if (entity.hasTime()) {
            fuelingBuilder.setTime(getDateTimeFormat().print(entity.getTime()));;
        }
        if (entity.hasCar()) {
            fuelingBuilder.setCar((SensorOuterClass.Sensor)sensorEncoder.encode(entity.getCar(), rights, mediaType));
        }
        if (entity.hasUser()) {
            fuelingBuilder.setUser((UserOuterClass.User)userEncoder.encode(entity.getUser(), rights, mediaType));;
        }
        if (entity.hasCreationTime()) {
            fuelingBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
        }
        if (entity.hasModificationTime()) {
            fuelingBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
        }
        if (entity.hasComment()) {
            fuelingBuilder.setComment(entity.getComment());
        }
        if (entity.hasIdentifier()) {
            fuelingBuilder.setId(entity.getIdentifier());
        }
        fuelingBuilder.setIsMissedFuelStop(entity.isMissedFuelStop());
        fuelingBuilder.setIsPartialFueling(entity.isPartialFueling());;
        return fuelingBuilder.build();
    }

    private FuelingOuterClass.Fueling.DimensionedNumber encode(DimensionedNumber dm) {
        return FuelingOuterClass.Fueling.DimensionedNumber.newBuilder()
                .setValue(dm.value().toString())
                .setUnit(dm.unit())
                .build();
    }
}
