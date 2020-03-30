package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.ActivityOuterClass;
import org.enviroCar.server.rest.GroupOuterClass;
import org.enviroCar.server.rest.TrackOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.activities.Activity;
import org.envirocar.server.core.activities.GroupActivity;
import org.envirocar.server.core.activities.TrackActivity;
import org.envirocar.server.core.activities.UserActivity;
import org.envirocar.server.core.entities.Group;
import org.envirocar.server.core.entities.Track;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class ActivityProtoMessageEncoder extends AbstractProtoMessageEncoder<Activity> {
    private final ProtoMessageEncoder<User> userEncoder;
    private final ProtoMessageEncoder<Track> trackEncoder;
    private final ProtoMessageEncoder<Group> groupEncoder;

    @Inject
    public ActivityProtoMessageEncoder(ProtoMessageEncoder<User> userEncoder,
                               ProtoMessageEncoder<Track> trackEncoder,
                               ProtoMessageEncoder<Group> groupEncoder) {
        super(Activity.class);
        this.userEncoder = userEncoder;
        this.trackEncoder = trackEncoder;
        this.groupEncoder = groupEncoder;
    }

    @Override
    public Message encode(Activity entity, AccessRights rights, MediaType mediaType) {
        ActivityOuterClass.Activity.Builder actBuilder = ActivityOuterClass.Activity.newBuilder();

        if (entity.hasTime()) {
            actBuilder.setTime(getDateTimeFormat().print(entity.getTime()));
        }
        if (entity.hasType()) {
            actBuilder.setType(entity.getType().toString());
        }
        if (entity.hasUser()) {
            actBuilder.setUser((UserOuterClass.User)userEncoder.encode(entity.getUser(), rights, mediaType));
        }
        if (entity instanceof GroupActivity) {
            GroupActivity activity = (GroupActivity) entity;
            if (activity.hasGroup()) {
                actBuilder.setGroup((GroupOuterClass.Group) groupEncoder.encode(activity.getGroup(), rights, mediaType));
            }
        } else if (entity instanceof UserActivity) {
            UserActivity activity = (UserActivity) entity;
            if (activity.hasOther()) {
                actBuilder.setOther((UserOuterClass.User) userEncoder.encode(activity.getOther(), rights, mediaType));
            }
        } else if (entity instanceof TrackActivity) {
            TrackActivity activity = (TrackActivity) entity;
            if (activity.hasTrack()) {
                actBuilder.setTrack((TrackOuterClass.Track) trackEncoder.encode(activity.getTrack(), rights, mediaType));
            }
        }
        return actBuilder.build();
    }
}
