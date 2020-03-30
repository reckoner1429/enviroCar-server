package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.FuelingOuterClass;
import org.envirocar.server.core.entities.Fueling;
import org.envirocar.server.core.entities.Fuelings;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class FuelingsProtoMessageEncoder extends AbstractProtoMessageEncoder<Fuelings> {
    private final ProtoMessageEncoder<Fueling> fuelingsEncoder;

    @Inject
    public FuelingsProtoMessageEncoder(ProtoMessageEncoder<Fueling> fuelingsEncoder) {
        super(Fuelings.class);
        this.fuelingsEncoder = fuelingsEncoder;
    }

    @Override
    public Message encode(Fuelings entity, AccessRights rights, MediaType mediaType) {
        FuelingOuterClass.FuelingCollection.Builder fuelingsCollection = FuelingOuterClass
                .FuelingCollection.newBuilder();
        for (Fueling fuelings : entity) {
            fuelingsCollection.addFueling((FuelingOuterClass.Fueling)fuelingsEncoder.encode(fuelings, rights, mediaType));
        }
        return fuelingsCollection.build();
    }
}
