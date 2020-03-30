package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.BadgeOuterClass;
import org.envirocar.server.core.entities.Badge;
import org.envirocar.server.core.entities.Badges;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class BadgesProtoMessageEncoder extends AbstractProtoMessageEncoder<Badges> {
    private final ProtoMessageEncoder<Badge> badgeEncoder;

    @Inject
    public BadgesProtoMessageEncoder(ProtoMessageEncoder<Badge> badgeEncoder) {
        super(Badges.class);
        this.badgeEncoder = badgeEncoder;
    }

    @Override
    public Message encode(Badges entity, AccessRights rights, MediaType mediaType) {
        BadgeOuterClass.BadgeCollection.Builder badgeCollection = BadgeOuterClass
                .BadgeCollection.newBuilder();
        for (Badge badge : entity) {
            badgeCollection.addBadge((BadgeOuterClass.Badge)badgeEncoder.encode(badge, rights, mediaType));
        }
        return badgeCollection.build();
    }
}
