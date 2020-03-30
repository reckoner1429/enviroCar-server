package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.GeometryOuterClass;
import org.enviroCar.server.rest.UserOuterClass;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;
import org.locationtech.jts.geom.Geometry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class UserProtoMessageEncoder extends AbstractProtoMessageEncoder<User> {
    private final ProtoMessageEncoder<Geometry> geometryEncoder;

    @Inject
    public UserProtoMessageEncoder(ProtoMessageEncoder<Geometry> geometryEncoder) {
        super(User.class);
        this.geometryEncoder = geometryEncoder;
    }

    @Override
    public Message encode(User entity, AccessRights rights, MediaType mediaType) {
        UserOuterClass.User.Builder userBuilder = UserOuterClass.User.newBuilder();

        if (entity.hasName() && rights.canSeeNameOf(entity)) {
            userBuilder.setName(entity.getName());
        }
        if (entity.hasMail() && rights.canSeeMailOf(entity)) {
            userBuilder.setMail(entity.getMail());
        }
        if (entity.hasCreationTime() && rights.canSeeCreationTimeOf(entity)) {
            userBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
        }
        if (entity.hasModificationTime() && rights.canSeeModificationTimeOf(entity)) {
            userBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
        }
        if (entity.hasFirstName() && rights.canSeeFirstNameOf(entity)) {
            userBuilder.setFirstName(entity.getFirstName());
        }
        if (entity.hasLastName() && rights.canSeeLastNameOf(entity)) {
            userBuilder.setLastName(entity.getLastName());
        }
        if (entity.hasGender() && rights.canSeeGenderOf(entity)) {
            switch (entity.getGender()) {
                case MALE:
                    userBuilder.setGender(UserOuterClass.User.Gender.MALE);
                    break;
                case FEMALE:
                    userBuilder.setGender(UserOuterClass.User.Gender.FEMALE);
                    break;
                default:
                    userBuilder.setGender(UserOuterClass.User.Gender.OTHER);
                    break;
            }
        }
        if (entity.hasDayOfBirth() && rights.canSeeDayOfBirthOf(entity)) {
            userBuilder.setDob(entity.getDayOfBirth());
        }
        if (entity.hasAboutMe() && rights.canSeeAboutMeOf(entity)) {
            userBuilder.setAboutMe(entity.getAboutMe());
        }
        if (entity.hasCountry() && rights.canSeeCountryOf(entity)) {
            userBuilder.setCountry(entity.getCountry());
        }
        if (entity.hasLocation() && rights.canSeeLocationOf(entity)) {
            userBuilder.setGeometry((GeometryOuterClass.Geometry)geometryEncoder.encode(entity.getLocation(), rights, mediaType));
        }
        if (entity.hasLanguage() && rights.canSeeLanguageOf(entity)) {
            userBuilder.setLanguage(entity.getLanguage());
        }
        if (entity.hasBadges() && rights.canSeeBadgesOf(entity)) {
            for(String badge : entity.getBadges()) {
                userBuilder.addBadge(badge);
            }
        }
        if (entity.hasTermsOfUseVersion() && rights.canSeeAcceptedTermsOfUseVersionOf(entity)) {
            userBuilder.setTermsOfUseVersion(entity.getTermsOfUseVersion());
        }
        if (entity.hasPrivacyStatementVersion() && rights.canSeeAcceptedPrivacyStatementVersionOf(entity)) {
            userBuilder.setPrivacyStatementVersion(entity.getPrivacyStatementVersion());
        }
        return userBuilder.build();
    }
}
