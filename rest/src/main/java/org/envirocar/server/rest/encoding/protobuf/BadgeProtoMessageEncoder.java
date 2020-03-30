package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.BadgeOuterClass;
import org.envirocar.server.core.entities.Badge;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
@Singleton
public class BadgeProtoMessageEncoder extends AbstractProtoMessageEncoder<Badge> {

    public BadgeProtoMessageEncoder() {
        super(Badge.class);
    }
    @Override
    public Message encode(Badge entity, AccessRights rights, MediaType mediaType) {
        BadgeOuterClass.Badge.Builder badgeBuildre = BadgeOuterClass.Badge.newBuilder();

        if (entity.getName() != null) {
            badgeBuildre.setName(entity.getName());
        }

        if (entity.getDisplayName() != null) {
            for (Map.Entry<String, String> e : entity.getDisplayName().entrySet()) {
                badgeBuildre.addDisplayName(BadgeOuterClass.Badge.Entry.newBuilder()
                        .setKey(e.getKey())
                        .setValue(e.getValue()));
            }
        }

        if (entity.getDescription() != null) {
            for (Map.Entry<String, String> e : entity.getDescription().entrySet()) {
                badgeBuildre.addDescription(BadgeOuterClass.Badge.Entry.newBuilder()
                        .setKey(e.getKey())
                        .setValue(e.getValue()));
            }
        }
        return badgeBuildre.build();
    }
}
