package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.TermsOuterClass;
import org.envirocar.server.core.entities.TermsOfUse;
import org.envirocar.server.core.entities.TermsOfUseInstance;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class TermsOfUseProtoMessageEncoder extends AbstractProtoMessageEncoder<TermsOfUse> {
    private final ProtoMessageEncoder<TermsOfUseInstance> termsOfUseEncoder;

    @Inject
    public TermsOfUseProtoMessageEncoder(ProtoMessageEncoder<TermsOfUseInstance> termsOfUseEncoder) {
        super(TermsOfUse.class);
        this.termsOfUseEncoder = termsOfUseEncoder;
    }

    @Override
    public Message encode(TermsOfUse entity, AccessRights rights, MediaType mediaType) {
        TermsOuterClass.TermsOfUse.Builder touBuilder = TermsOuterClass.TermsOfUse.newBuilder();

        for (TermsOfUseInstance termsOfUseInstance : entity) {
            touBuilder.addTerms((TermsOuterClass.Terms)termsOfUseEncoder.encode(termsOfUseInstance, rights, mediaType));
        }
        return touBuilder.build();
    }
}
