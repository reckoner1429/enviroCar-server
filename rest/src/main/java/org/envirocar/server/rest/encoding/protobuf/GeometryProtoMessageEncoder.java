package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.envirocar.server.core.exception.GeometryConverterException;
import org.envirocar.server.rest.InternalServerError;
import org.envirocar.server.rest.rights.AccessRights;
import org.envirocar.server.rest.util.GeoProto;
import org.locationtech.jts.geom.Geometry;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class GeometryProtoMessageEncoder extends AbstractProtoMessageEncoder<Geometry> {
    private final GeoProto geoProto;

    @Inject
    GeometryProtoMessageEncoder(GeoProto geoProto) {
        super(Geometry.class);
        this.geoProto = geoProto;
    }
    @Override
    public Message encode(Geometry entity, AccessRights rights, MediaType mediaType) {
        try {
            return geoProto.encode(entity);
        } catch (GeometryConverterException ex) {
            throw new InternalServerError(ex);
        }
    }
}
