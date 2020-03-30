package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.GroupOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.entities.Group;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class GroupProtoMessageEncoder extends AbstractProtoMessageEncoder<Group> {
    private final ProtoMessageEncoder<User> userEncoder;

    @Inject
    public GroupProtoMessageEncoder(ProtoMessageEncoder<User> userEncoder) {
        super(Group.class);
        this.userEncoder = userEncoder;
    }
    @Override
    public Message encode(Group entity, AccessRights rights, MediaType mediaType) {
        GroupOuterClass.Group.Builder grpBuilder = GroupOuterClass.Group.newBuilder();

        if (entity.hasName() && rights.canSeeNameOf(entity)) {
            grpBuilder.setName(entity.getName());
        }
        if (entity.hasDescription() && rights.canSeeDescriptionOf(entity)) {
            grpBuilder.setDescription(entity.getDescription());
        }
        if (entity.hasOwner() && rights.canSeeOwnerOf(entity)) {
            grpBuilder.setOwner((UserOuterClass.User) userEncoder.encode(entity.getOwner(), rights, mediaType));
        }
        if (entity.hasCreationTime() && rights.canSeeCreationTimeOf(entity)) {
            grpBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
        }
        if (entity.hasModificationTime() && rights.canSeeModificationTimeOf(entity)) {
            grpBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
        }
        return grpBuilder.build();
    }
}
