package org.envirocar.server.rest.encoding.protobuf;

import com.google.inject.Inject;
import com.google.protobuf.Message;
import org.enviroCar.server.rest.TrackOuterClass;
import org.envirocar.server.core.entities.Track;
import org.envirocar.server.core.entities.Tracks;
import org.envirocar.server.rest.encoding.ProtoMessageEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class TracksProtoMessageEncoder extends AbstractProtoMessageEncoder<Tracks> {
    private final ProtoMessageEncoder<Track> trackEncoder;

    @Inject
    public TracksProtoMessageEncoder(ProtoMessageEncoder<Track> trackEncoder) {
        super(Tracks.class);
        this.trackEncoder = trackEncoder;
    }

    @Override
    public Message encode(Tracks entity, AccessRights rights, MediaType mediaType) {
        TrackOuterClass.TrackCollection.Builder trackCollection = TrackOuterClass
                .TrackCollection.newBuilder();
        for (Track track : entity) {
            trackCollection.addTrack((TrackOuterClass.Track)trackEncoder.encode(track, rights, mediaType));
        }
        return trackCollection.build();
    }
}
