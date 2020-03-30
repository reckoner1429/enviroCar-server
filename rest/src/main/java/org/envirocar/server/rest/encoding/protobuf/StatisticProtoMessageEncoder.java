package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.PhenomenonOuterClass;
import org.enviroCar.server.rest.StatisticOuterClass;
import org.envirocar.server.core.entities.Phenomenon;
import org.envirocar.server.core.statistics.Statistic;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class StatisticProtoMessageEncoder extends AbstractProtoMessageEncoder<Statistic> {
    private final ProtoMessageEncoder<Phenomenon> phenomenonEncoder;

    @Inject
    public StatisticProtoMessageEncoder(ProtoMessageEncoder<Phenomenon> phenomenonEncoder) {
        super(Statistic.class);
        this.phenomenonEncoder = phenomenonEncoder;
    }

    @Override
    public Message encode(Statistic entity, AccessRights rights, MediaType mediaType) {
        StatisticOuterClass.Statistic.Builder statBuilder = StatisticOuterClass.Statistic.newBuilder();
        statBuilder.setMax(entity.getMax());
        statBuilder.setMean(entity.getMean());
        statBuilder.setMin(entity.getMin());
        statBuilder.setMeasurements(entity.getMeasurements());
        statBuilder.setTracks(entity.getTracks());
        statBuilder.setUsers(entity.getUsers());
        statBuilder.setSensors(entity.getSensors());
        statBuilder.setPhenomenon((PhenomenonOuterClass.Phenomenon)phenomenonEncoder
                .encode(entity.getPhenomenon(), rights, mediaType));
        return statBuilder.build();
    }
}
