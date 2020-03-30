package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.core.entities.Users;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class UsersProtoMessageEncoder extends AbstractProtoMessageEncoder<Users> {
    private final ProtoMessageEncoder<User> userEncoder;

    @Inject
    public UsersProtoMessageEncoder(ProtoMessageEncoder<User> userEncoder) {
        super(Users.class);
        this.userEncoder = userEncoder;
    }

    @Override
    public Message encode(Users entity, AccessRights rights, MediaType mediaType) {
        UserOuterClass.UserCollection.Builder userCollection = UserOuterClass
                .UserCollection.newBuilder();
        for (User user : entity) {
            userCollection.addUser((UserOuterClass.User)userEncoder.encode(user, rights, mediaType));
        }
        return userCollection.build();
    }
}
