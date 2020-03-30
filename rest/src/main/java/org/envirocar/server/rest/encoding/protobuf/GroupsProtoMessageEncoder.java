package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.GroupOuterClass;
import org.envirocar.server.core.entities.Group;
import org.envirocar.server.core.entities.Groups;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class GroupsProtoMessageEncoder extends AbstractProtoMessageEncoder<Groups> {
    private final ProtoMessageEncoder<Group> groupEncoder;

    @Inject
    public GroupsProtoMessageEncoder(ProtoMessageEncoder<Group> groupEncoder) {
        super(Groups.class);
        this.groupEncoder = groupEncoder;
    }

    @Override
    public Message encode(Groups entity, AccessRights rights, MediaType mediaType) {
        GroupOuterClass.GroupCollection.Builder groupCollection = GroupOuterClass
                .GroupCollection.newBuilder();
        for (Group group : entity) {
            groupCollection.addGroup((GroupOuterClass.Group)groupEncoder.encode(group, rights, mediaType));
        }
        return groupCollection.build();
    }
}
