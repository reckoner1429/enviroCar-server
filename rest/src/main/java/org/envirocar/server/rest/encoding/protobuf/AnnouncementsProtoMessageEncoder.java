package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.AnnouncementOuterClass;
import org.envirocar.server.core.entities.Announcement;
import org.envirocar.server.core.entities.Announcements;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class AnnouncementsProtoMessageEncoder extends AbstractProtoMessageEncoder<Announcements> {
    private final ProtoMessageEncoder<Announcement> announcementEncoder;

    @Inject
    public AnnouncementsProtoMessageEncoder(ProtoMessageEncoder<Announcement> announcementEncoder) {
        super(Announcements.class);
        this.announcementEncoder = announcementEncoder;
    }

    @Override
    public Message encode(Announcements entity, AccessRights rights, MediaType mediaType) {
        AnnouncementOuterClass.AnnouncementCollection.Builder announcementCollection = AnnouncementOuterClass
                .AnnouncementCollection.newBuilder();
        for (Announcement announcement : entity) {
            announcementCollection.addAnnouncement((AnnouncementOuterClass.Announcement)announcementEncoder
                    .encode(announcement, rights, mediaType));
        }
        return announcementCollection.build();
    }
}
