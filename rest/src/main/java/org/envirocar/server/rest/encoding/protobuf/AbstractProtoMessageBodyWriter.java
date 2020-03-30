package org.envirocar.server.rest.encoding.protobuf;

import com.google.protobuf.Message;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces(MediaType.APPLICATION_OCTET_STREAM)
public abstract class AbstractProtoMessageBodyWriter<T> implements MessageBodyWriter<T> {
    private final Class<T> classType;

    public AbstractProtoMessageBodyWriter(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return this.classType.isAssignableFrom(type) && mediaType.isCompatible(MediaType.APPLICATION_OCTET_STREAM_TYPE);
    }


    @Override
    public void writeTo(T entity, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream out)
            throws IOException, WebApplicationException {
        Message message = encode(entity, mediaType);
        message.writeTo(out);
        out.flush();
    }


    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public abstract Message encode(T entity, MediaType mediaType);

}
