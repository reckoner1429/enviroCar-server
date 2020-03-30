package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.TermsOuterClass;
import org.envirocar.server.core.entities.Terms;
import org.envirocar.server.rest.rights.AccessRights;

import javax.ws.rs.core.MediaType;

public class TermsProtoMessageEncoder<T extends Terms> extends AbstractProtoMessageEncoder<T> {
    final String schema;
    public TermsProtoMessageEncoder(Class<T> classType, String schema) {
        super(classType);
        this.schema = schema;
    }

    @Override
    public Message encode(T entity, AccessRights rights, MediaType mediaType) {

        TermsOuterClass.Terms.Builder termsBuilder = TermsOuterClass.Terms.newBuilder();
        if (entity.getIdentifier() != null) {
            termsBuilder.setId(entity.getIdentifier());
        }
        if (entity.getIssuedDate() != null) {
            termsBuilder.setIssueDate(entity.getIssuedDate());
        }
        if (getSchemaUriConfiguration().isSchema(mediaType, schema)) {
            if (entity.hasCreationTime()) {
                termsBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
            }
            if (entity.hasModificationTime()) {
                termsBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
            }
            if (entity.getContents() != null) {
                termsBuilder.setContents(entity.getContents());
            }
        }
        return termsBuilder.build();
    }
}
