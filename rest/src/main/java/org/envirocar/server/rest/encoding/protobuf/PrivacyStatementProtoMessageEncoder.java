package org.envirocar.server.rest.encoding.protobuf;

import org.envirocar.server.core.entities.PrivacyStatement;
import org.envirocar.server.rest.Schemas;

public class PrivacyStatementProtoMessageEncoder extends TermsProtoMessageEncoder<PrivacyStatement> {
    public PrivacyStatementProtoMessageEncoder() {
        super(PrivacyStatement.class, Schemas.PRIVACY_STATEMENT);
    }
}
