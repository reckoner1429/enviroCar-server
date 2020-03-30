package org.envirocar.server.rest.encoding.protobuf;

import org.envirocar.server.core.entities.TermsOfUseInstance;
import org.envirocar.server.rest.Schemas;

public class TermsOfUseInstProtoMessageEncoder extends TermsProtoMessageEncoder<TermsOfUseInstance> {
    public TermsOfUseInstProtoMessageEncoder() {
        super(TermsOfUseInstance.class, Schemas.TERMS_OF_USE_INSTANCE);
    }
}