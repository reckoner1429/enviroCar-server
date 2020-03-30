package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.ActivityOuterClass;
import org.envirocar.server.core.activities.Activities;
import org.envirocar.server.core.activities.Activity;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class ActivitiesProtoMessageEncoder extends AbstractProtoMessageEncoder<Activities> {
    private final ProtoMessageEncoder<Activity> activityEncoder;

    @Inject
    public ActivitiesProtoMessageEncoder(ProtoMessageEncoder<Activity> activityEncoder) {
        super(Activities.class);
        this.activityEncoder = activityEncoder;
    }

    @Override
    public Message encode(Activities entity, AccessRights rights, MediaType mediaType) {
        ActivityOuterClass.ActivityCollection.Builder activityCollection = ActivityOuterClass
                .ActivityCollection.newBuilder();
        for (Activity activity : entity) {
            activityCollection.addActivity((ActivityOuterClass.Activity)activityEncoder.encode(activity, rights, mediaType));
        }
        return activityCollection.build();
    }
}
