package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.protobuf.Message;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;
import org.envirocar.server.rest.schema.JsonSchemaUriConfiguration;
import org.joda.time.format.DateTimeFormatter;

import javax.ws.rs.core.MediaType;
import java.io.OutputStream;

public abstract class AbstractProtoMessageEncoder<T> extends AbstractProtoMessageBodyWriter<T> implements ProtoMessageEncoder<T> {
    private DateTimeFormatter dateTimeFormat;
    private Provider<AccessRights> rights;
    private JsonSchemaUriConfiguration schemaUriConfiguration;

    public AbstractProtoMessageEncoder(Class<T> classType) {
        super(classType);
    }

    public DateTimeFormatter getDateTimeFormat() {
        return dateTimeFormat;
    }

    @Inject
    public void setDateTimeFormat(DateTimeFormatter dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    @Inject
    public void setRights(Provider<AccessRights> rights) {
        this.rights = rights;
    }

    @Override
    public Message encode(T entity, MediaType mediaType, OutputStream out) {
        return encode(entity, rights.get(), mediaType);
    }

    @Inject
    public void setSchemaUriConfiguration(JsonSchemaUriConfiguration schemaUriConfiguration) {
        this.schemaUriConfiguration = schemaUriConfiguration;
    }

    protected JsonSchemaUriConfiguration getSchemaUriConfiguration() {
        return schemaUriConfiguration;
    }
}
