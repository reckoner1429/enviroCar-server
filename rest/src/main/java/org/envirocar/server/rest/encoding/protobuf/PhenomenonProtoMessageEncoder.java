package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.PhenomenonOuterClass;
import org.envirocar.server.core.entities.Phenomenon;
import org.envirocar.server.rest.Schemas;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class PhenomenonProtoMessageEncoder extends AbstractProtoMessageEncoder<Phenomenon> {

    PhenomenonProtoMessageEncoder() {
        super(Phenomenon.class);
    }
    @Override
    public Message encode(Phenomenon entity, AccessRights rights, MediaType mediaType) {
        PhenomenonOuterClass.Phenomenon.Builder phenoBuilder = PhenomenonOuterClass.Phenomenon.newBuilder();

        if (entity.hasName()) {
            phenoBuilder.setName(entity.getName());
        }
        if (entity.hasUnit()) {
            phenoBuilder.setUnit(entity.getUnit());
        }
        if (getSchemaUriConfiguration().isSchema(mediaType, Schemas.PHENOMENON)) {
            if (entity.hasCreationTime()) {
                phenoBuilder.setCreated(getDateTimeFormat().print(entity.getCreationTime()));
            }
            if (entity.hasModificationTime()) {
                phenoBuilder.setModified(getDateTimeFormat().print(entity.getModificationTime()));
            }
        }
        return phenoBuilder.build();
    }
}
