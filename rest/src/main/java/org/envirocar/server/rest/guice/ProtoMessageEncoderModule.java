package org.envirocar.server.rest.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import org.envirocar.server.core.activities.Activities;
import org.envirocar.server.core.activities.Activity;
import org.envirocar.server.core.entities.*;
import org.envirocar.server.core.statistics.Statistic;
import org.envirocar.server.core.statistics.Statistics;
import org.envirocar.server.rest.encoding.JSONEntityEncoder;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.encoding.json.ErrorMessageJSONEncoder;
import org.envirocar.server.rest.encoding.protobuf.*;
import org.envirocar.server.rest.util.ErrorMessage;
import org.locationtech.jts.geom.Geometry;

public class ProtoMessageEncoderModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(new TypeLiteral<ProtoMessageEncoder<User>>() {}).to(UserProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Users>>() {}).to(UsersProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Sensor>>() {}).to(SensorProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Sensors>>() {}).to(SensorsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Sensors>>() {}).to(SensorsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Track>>() {}).to(TrackProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Tracks>>() {}).to(TracksProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Measurement>>() {}).to(MeasurementProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Measurements>>() {}).to(MeasurementsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Phenomenon>>() {}).to(PhenomenonProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Phenomenons>>() {}).to(PhenomenonsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Group>>() {}).to(GroupProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Groups>>() {}).to(GroupsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Statistic>>() {}).to(StatisticProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Statistics>>() {}).to(StatisticsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Activity>>() {}).to(ActivityProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Activities>>() {}).to(ActivitiesProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Geometry>>() {}).to(GeometryProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<TermsOfUseInstance>>() {}).to(TermsOfUseInstProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<TermsOfUse>>() {}).to(TermsOfUseProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<PrivacyStatement>>() {}).to(PrivacyStatementProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<PrivacyStatements>>() {}).to(PrivacyStatementsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Announcement>>() {}).to(AnnouncementProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Announcements>>() {}).to(AnnouncementsProtoMessageEncoder.class);
        bind(new TypeLiteral<ProtoMessageEncoder<Badge>>() {}).to(BadgeProtoMessageEncoder.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<ProtoMessageEncoder<Badges>>() {}).to(BadgesProtoMessageEncoder.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<ProtoMessageEncoder<Fueling>>() {}).to(FuelingProtoMessageEncoder.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<ProtoMessageEncoder<Fuelings>>() {}).to(FuelingsProtoMessageEncoder.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<ProtoMessageEncoder<UserStatistic>>() {}).to(UserStatisticProtoMessageEncoder.class).in(Scopes.SINGLETON);
        bind(new TypeLiteral<JSONEntityEncoder<ErrorMessage>>() {}).to(ErrorMessageJSONEncoder.class).in(Scopes.SINGLETON);

        bind(UserProtoMessageEncoder.class);
        bind(UsersProtoMessageEncoder.class);
        bind(SensorProtoMessageEncoder.class);
        bind(SensorsProtoMessageEncoder.class);
        bind(TrackProtoMessageEncoder.class);
        bind(TracksProtoMessageEncoder.class);
        bind(MeasurementProtoMessageEncoder.class);
        bind(MeasurementsProtoMessageEncoder.class);
        bind(PhenomenonProtoMessageEncoder.class);
        bind(PhenomenonsProtoMessageEncoder.class);
        bind(GroupProtoMessageEncoder.class);
        bind(GroupsProtoMessageEncoder.class);
        bind(StatisticProtoMessageEncoder.class);
        bind(StatisticsProtoMessageEncoder.class);
        bind(ActivityProtoMessageEncoder.class);
        bind(ActivitiesProtoMessageEncoder.class);
        bind(GeometryProtoMessageEncoder.class);
        bind(TermsOfUseInstProtoMessageEncoder.class);
        bind(TermsOfUseProtoMessageEncoder.class);
        bind(PrivacyStatementProtoMessageEncoder.class);
        bind(PrivacyStatementsProtoMessageEncoder.class);
        bind(AnnouncementProtoMessageEncoder.class);
        bind(AnnouncementsProtoMessageEncoder.class);
        bind(BadgeProtoMessageEncoder.class);
        bind(BadgesProtoMessageEncoder.class);
        bind(FuelingProtoMessageEncoder.class);
        bind(FuelingsProtoMessageEncoder.class);
        bind(UserStatisticProtoMessageEncoder.class);
        bind(ErrorMessageJSONEncoder.class);
    }
}
