package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.TermsOuterClass;
import org.envirocar.server.core.entities.PrivacyStatement;
import org.envirocar.server.core.entities.PrivacyStatements;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class PrivacyStatementsProtoMessageEncoder extends AbstractProtoMessageEncoder<PrivacyStatements> {
    private final ProtoMessageEncoder<PrivacyStatement> pvSmtEncoder;

    @Inject
    public PrivacyStatementsProtoMessageEncoder(ProtoMessageEncoder<PrivacyStatement> pvSmtEncoder) {
        super(PrivacyStatements.class);
        this.pvSmtEncoder = pvSmtEncoder;
    }

    @Override
    public Message encode(PrivacyStatements entity, AccessRights rights, MediaType mediaType) {
        TermsOuterClass.PrivacyStatement.Builder privacyStatement = TermsOuterClass
                .PrivacyStatement.newBuilder();
        for (PrivacyStatement smt : entity) {
            privacyStatement.addTerms((TermsOuterClass.Terms)pvSmtEncoder.encode(smt, rights, mediaType));
        }
        return privacyStatement.build();
    }
}
