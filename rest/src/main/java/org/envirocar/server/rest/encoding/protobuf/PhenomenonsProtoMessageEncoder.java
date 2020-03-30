package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.PhenomenonOuterClass;
import org.envirocar.server.core.entities.Phenomenon;
import org.envirocar.server.core.entities.Phenomenons;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class PhenomenonsProtoMessageEncoder extends AbstractProtoMessageEncoder<Phenomenons> {
    private final ProtoMessageEncoder<Phenomenon> phenomenonEncoder;

    @Inject
    public PhenomenonsProtoMessageEncoder(ProtoMessageEncoder<Phenomenon> phenomenonEncoder) {
        super(Phenomenons.class);
        this.phenomenonEncoder = phenomenonEncoder;
    }

    @Override
    public Message encode(Phenomenons entity, AccessRights rights, MediaType mediaType) {
        PhenomenonOuterClass.PhenomenonCollection.Builder phenomenonCollection = PhenomenonOuterClass
                .PhenomenonCollection.newBuilder();
        for (Phenomenon phenomenon : entity) {
            phenomenonCollection.addPhenomenon((PhenomenonOuterClass.Phenomenon)phenomenonEncoder.encode(phenomenon, rights, mediaType));
        }
        return phenomenonCollection.build();
    }
}
