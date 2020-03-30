package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.AnnouncementOuterClass;
import org.envirocar.server.core.entities.Announcement;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
@Singleton
public class AnnouncementProtoMessageEncoder extends AbstractProtoMessageEncoder<Announcement> {


    public AnnouncementProtoMessageEncoder() {
        super(Announcement.class);
    }

    @Override
    public Message encode(Announcement entity, AccessRights rights, MediaType mediaType) {
        AnnouncementOuterClass.Announcement.Builder announcementBuilder = AnnouncementOuterClass.Announcement.newBuilder();

        if (entity.hasCreationTime()) {
            announcementBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
        }
        if (entity.hasModificationTime()) {
            announcementBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));;
        }
        if (entity.getIdentifier() != null) {
            announcementBuilder.setId(entity.getIdentifier());
        }
        if (entity.getVersions() != null) {
            announcementBuilder.setVersions(entity.getIdentifier());
        }
        if (entity.getCategory() != null) {
            announcementBuilder.setCategory(entity.getCategory());
        }
        if (entity.getPriority() != null) {
            announcementBuilder.setPriority(entity.getPriority());
        }
        if (entity.getContents() != null) {
            for (Map.Entry<String, String> entry : entity.getContents().entrySet()) {
                announcementBuilder.addEntry(AnnouncementOuterClass.Announcement.Entry.newBuilder()
                                .setKey(entry.getKey())
                                .setValue(entry.getValue()));
            }
        }
        return announcementBuilder.build();
    }
}
