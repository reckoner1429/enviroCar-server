package org.envirocar.server.rest.encoding;

import com.google.protobuf.Message;
import org.enviroCar.server.rest.MeasurementOuterClass;
import org.envirocar.server.core.entities.Measurement;
import org.envirocar.server.rest.rights.AccessRights;

import javax.ws.rs.core.MediaType;
import java.io.OutputStream;

public interface ProtoMessageEncoder<T> {
    Message encode(T entity, AccessRights rights, MediaType mediaType);
}
