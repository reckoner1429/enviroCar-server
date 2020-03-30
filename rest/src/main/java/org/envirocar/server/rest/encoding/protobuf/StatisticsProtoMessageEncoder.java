package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.StatisticOuterClass;
import org.envirocar.server.core.statistics.Statistic;
import org.envirocar.server.core.statistics.Statistics;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class StatisticsProtoMessageEncoder extends AbstractProtoMessageEncoder<Statistics> {
    private final ProtoMessageEncoder<Statistic> statisticEncoder;

    @Inject
    public StatisticsProtoMessageEncoder(ProtoMessageEncoder<Statistic> statisticEncoder) {
        super(Statistics.class);
        this.statisticEncoder = statisticEncoder;
    }

    @Override
    public Message encode(Statistics entity, AccessRights rights, MediaType mediaType) {
        StatisticOuterClass.StatisticCollection.Builder statisticCollection = StatisticOuterClass
                .StatisticCollection.newBuilder();
        for (Statistic statistic : entity) {
            statisticCollection.addStatistic((StatisticOuterClass.Statistic)statisticEncoder.encode(statistic, rights, mediaType));
        }
        return statisticCollection.build();
    }
}
