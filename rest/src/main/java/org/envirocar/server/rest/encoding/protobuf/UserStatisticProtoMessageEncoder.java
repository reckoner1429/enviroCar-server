package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.GeometryOuterClass;
import org.enviroCar.server.rest.UserStatisticOuterClass;
import org.envirocar.server.core.entities.TrackSummary;
import org.envirocar.server.core.entities.UserStatistic;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;
import org.locationtech.jts.geom.Geometry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class UserStatisticProtoMessageEncoder implements ProtoMessageEncoder<UserStatistic> {
    private final ProtoMessageEncoder<Geometry> geometryEncoder;

    @Inject
    public UserStatisticProtoMessageEncoder(ProtoMessageEncoder<Geometry> geometryEncoder) {
        this.geometryEncoder = geometryEncoder;
    }


    @Override
    public Message encode(UserStatistic entity, AccessRights rights, MediaType mediaType) {
        UserStatisticOuterClass.UserStatistic.Builder userStatBuilder = UserStatisticOuterClass.UserStatistic.newBuilder();

        userStatBuilder.setDistance(entity.getDistance());
        userStatBuilder.setDuration(entity.getDuration());
        userStatBuilder.setDistanceBelow60Kmh(entity.getDistanceBelow60kmh());
        userStatBuilder.setDurationBelow60Kmh(entity.getDurationBelow60kmh());
        userStatBuilder.setDistanceAbove130Kmh(entity.getDistanceAbove130kmh());
        userStatBuilder.setDistanceNan(entity.getDistanceNaN());
        userStatBuilder.setDurationNan(entity.getDurationNaN());



        if (entity.hasTrackSummaries()) {
            for (TrackSummary trackSummary : entity.getTrackSummaries()) {
                userStatBuilder.addTracksummary(encodeTrackSummary(trackSummary, rights, mediaType));
            }
        }
        return userStatBuilder.build();
    }

    private UserStatisticOuterClass.TrackSummary encodeTrackSummary(TrackSummary trackSummary, AccessRights rights, MediaType mediaType) {
        UserStatisticOuterClass.TrackSummary.Builder trackSummaryBuilder = UserStatisticOuterClass.TrackSummary.newBuilder();

        if (trackSummary.hasIdentifier()) {
            trackSummaryBuilder.setId(trackSummary.getIdentifier());
        }
        if (trackSummary.hasStartPosition()) {
            trackSummaryBuilder.setStartPosition((GeometryOuterClass.Geometry)geometryEncoder
                    .encode(trackSummary.getStartPosition(), rights, mediaType));
        }
        if (trackSummary.hasEndPosition()) {
            trackSummaryBuilder.setStopPosition((GeometryOuterClass.Geometry)geometryEncoder
                    .encode(trackSummary.getEndPosition(), rights, mediaType));
        }
        return trackSummaryBuilder.build();
    }
}
