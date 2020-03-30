/*
 * Copyright (C) 2013-2020 The enviroCar project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.envirocar.server.rest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import org.enviroCar.server.rest.GeometryOuterClass;
import org.geotools.geometry.GeometryBuilder;
import org.locationtech.jts.geom.*;
import org.envirocar.server.core.exception.GeometryConverterException;
import org.envirocar.server.core.util.GeometryConverter;

import static org.envirocar.server.core.util.GeoJSONConstants.*;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class GeoProto 
//        implements GeometryConverter<GeometryOuterClass.GeometryCollection> 
{
    protected final GeometryFactory geometryFactory;

    @Inject
    public GeoProto(GeometryFactory geometryFactory, JsonNodeCreator jsonFactory) {
        this.geometryFactory = geometryFactory;
    }

    private GeometryFactory getGeometryFactory() {
        return geometryFactory;
    }

//    @Override
    public GeometryOuterClass.Geometry encode(Geometry value) throws GeometryConverterException {
        return value == null ? null : encodeGeometry(value);
    }
//
//    @Override
//    public Geometry decode(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        return json == null ? null : decodeGeometry(json);
//    }

    private GeometryOuterClass.Geometry encodeGeometry(Geometry geometry) throws GeometryConverterException {
        Preconditions.checkNotNull(geometry);
        if (geometry.isEmpty()) {
            return null;
        } else if (geometry instanceof Point) {
            return encode((Point) geometry);
        } else if (geometry instanceof LineString) {
            return encode((LineString) geometry);
        } else if (geometry instanceof Polygon) {
            return encode((Polygon) geometry);
        } else if (geometry instanceof MultiPoint) {
            return encode((MultiPoint) geometry);
        } else if (geometry instanceof MultiLineString) {
            return encode((MultiLineString) geometry);
        } else if (geometry instanceof MultiPolygon) {
            return encode((MultiPolygon) geometry);
        } else if (geometry instanceof GeometryCollection) {
            return encode((GeometryCollection) geometry);
        } else {
            throw new GeometryConverterException(String.format("unknown geometry type %s", geometry.getGeometryType()));
        }
    }

//    @Override
    public GeometryOuterClass.Geometry encode(Point geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setPoint(encodeCoordinates(geometry));
        
        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(LineString geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setLine(encodeCoordinates(geometry));

        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(Polygon geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setPolygon(encodeCoordinates(geometry));

        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(MultiPoint geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.MultiPoint.Builder multiPointBuilder = GeometryOuterClass.MultiPoint.newBuilder();
        
        for (int i = 0; i < geometry.getNumGeometries(); ++i) {
            multiPointBuilder.setPoint(i, encodeCoordinates((Point) geometry.getGeometryN(i)));
        }

        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setMultiPoint(multiPointBuilder.build());

        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(MultiLineString geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.MultiLine.Builder multiLineBuilder = GeometryOuterClass.MultiLine.newBuilder();

        for (int i = 0; i < geometry.getNumGeometries(); ++i) {
            multiLineBuilder.setLine(i, encodeCoordinates((LineString) geometry.getGeometryN(i)));
        }

        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setMultiLine(multiLineBuilder);

        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(MultiPolygon geometry) {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.MultiPolygon.Builder multiPolygonBuilder = GeometryOuterClass.MultiPolygon.newBuilder();

        for (int i = 0; i < geometry.getNumGeometries(); ++i) {
            multiPolygonBuilder.setPolygon(i, encodeCoordinates((Polygon) geometry.getGeometryN(i)));
        }
        GeometryOuterClass.Geometry.Builder geoBuilder = GeometryOuterClass.Geometry.newBuilder()
                .setMultiPolygon(multiPolygonBuilder);

        return geoBuilder.build();
    }

//    @Override
    public GeometryOuterClass.Geometry encode(GeometryCollection geometry) throws GeometryConverterException {
        Preconditions.checkNotNull(geometry);
        GeometryOuterClass.GeometryCollection.Builder geoCollectionBuilder = GeometryOuterClass.GeometryCollection.newBuilder();
        
        for (int i = 0; i < geometry.getNumGeometries(); ++i) {
            geoCollectionBuilder.setGeometryCollection(i, encodeGeometry(geometry.getGeometryN(i)));
        }

        return GeometryOuterClass.Geometry.newBuilder()
                .setGeometryCollection(geoCollectionBuilder.build())
                .build();
    }

    private GeometryOuterClass.Point encodeCoordinate(Coordinate coordinate) {
        return GeometryOuterClass.Point.newBuilder()
                .setCoordinate(GeometryOuterClass.Coordinate.newBuilder()
                .setX(coordinate.x)
                .setY(coordinate.y)
                .build())
                .build();
    }

//    private ArrayNode encodeCoordinates(CoordinateSequence coordinates) {
//        ArrayNode list = getJsonFactory().arrayNode();
//        for (int i = 0; i < coordinates.size(); ++i) {
//            ArrayNode coordinate = getJsonFactory().arrayNode();
//            coordinate.add(coordinates.getX(i));
//            coordinate.add(coordinates.getY(i));
//            list.add(coordinate);
//        }
//        return list;
//    }

    private GeometryOuterClass.Point encodeCoordinates(Point geometry) {
        return encodeCoordinate(geometry.getCoordinate());
    }

    private GeometryOuterClass.Line encodeCoordinates(LineString geometry) {
        CoordinateSequence coordinateSequence = geometry.getCoordinateSequence();
        GeometryOuterClass.Line.Builder lineBuilder = GeometryOuterClass.Line.newBuilder();
        for(int i = 0; i < coordinateSequence.size(); ++i) {
            lineBuilder.setCoordinate(i, encodeCoordinate(coordinateSequence.getCoordinate(i)));
        }
        return lineBuilder.build();
    }

    private GeometryOuterClass.Polygon encodeCoordinates(Polygon geometry) {
        GeometryOuterClass.Polygon.Builder polyBuilder = GeometryOuterClass.Polygon.newBuilder();

        polyBuilder.setLine(0, encodeCoordinates(geometry.getExteriorRing()));
        for (int i = 0; i < geometry.getNumInteriorRing(); ++i) {
            polyBuilder.setLine(i+1,encodeCoordinates(geometry.getInteriorRingN(i)));
        }
        return polyBuilder.build();
    }

//    private ArrayNode requireCoordinates(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        if (!json.has(COORDINATES_KEY)) {
//            throw new GeometryConverterException("missing 'coordinates' field");
//        }
//        return toList(json.get(COORDINATES_KEY));
//    }

//    private Coordinate decodeCoordinate(ArrayNode list) throws GeometryConverterException {
//        if (list.size() != 2) {
//            throw new GeometryConverterException("coordinates may only have 2 dimensions");
//        }
//        Number x = list.get(0).numberValue();
//        Number y = list.get(1).numberValue();
//        if (x == null || y == null) {
//            throw new GeometryConverterException("x and y have to be numbers");
//        }
//        return new Coordinate(x.doubleValue(), y.doubleValue());
//    }
//
//    private ArrayNode toList(Object o) throws GeometryConverterException {
//        if (!(o instanceof ArrayNode)) {
//            throw new GeometryConverterException("expected list");
//        }
//        return (ArrayNode) o;
//    }
//
//    private Coordinate[] decodeCoordinates(ArrayNode list) throws GeometryConverterException {
//        Coordinate[] coordinates = new Coordinate[list.size()];
//        for (int i = 0; i < list.size(); ++i) {
//            coordinates[i] = decodeCoordinate(toList(list.get(i)));
//        }
//        return coordinates;
//    }
//
//    private Polygon decodePolygonCoordinates(ArrayNode coordinates) throws GeometryConverterException {
//        if (coordinates.size() < 1) {
//            throw new GeometryConverterException("missing polygon shell");
//        }
//        LinearRing shell = getGeometryFactory()
//                .createLinearRing(decodeCoordinates(toList(coordinates.get(0))));
//        LinearRing[] holes = new LinearRing[coordinates.size() - 1];
//        for (int i = 1; i < coordinates.size(); ++i) {
//            holes[i - 1] = getGeometryFactory()
//                    .createLinearRing(decodeCoordinates(toList(coordinates
//                            .get(i))));
//        }
//        return getGeometryFactory().createPolygon(shell, holes);
//    }
//
//    private Geometry decodeGeometry(GeometryOuterClass.GeometryCollection o) throws GeometryConverterException {
//        if (!(o instanceof GeometryOuterClass.GeometryCollection)) {
//            throw new GeometryConverterException(String.format("Cannot decode %s", o));
//        }
//        GeometryOuterClass.GeometryCollection json = (GeometryOuterClass.GeometryCollection) o;
//        if (!json.has(TYPE_KEY)) {
//            throw new GeometryConverterException("Can not determine geometry type (missing 'type' field)");
//        }
//        String type = json.path(TYPE_KEY).textValue();
//        switch (type) {
//            case POINT_TYPE:
//                return decodePoint(json);
//            case MULTI_POINT_TYPE:
//                return decodeMultiPoint(json);
//            case LINE_STRING_TYPE:
//                return decodeLineString(json);
//            case MULTI_LINE_STRING_TYPE:
//                return decodeMultiLineString(json);
//            case POLYGON_TYPE:
//                return decodePolygon(json);
//            case MULTI_POLYGON_TYPE:
//                return decodeMultiPolygon(json);
//            case GEOMETRY_COLLECTION_TYPE:
//                return decodeGeometryCollection(json);
//            default:
//                throw new GeometryConverterException("Unknown geometry type: " + type);
//        }
//    }
//
//    @Override
//    public MultiLineString decodeMultiLineString(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        ArrayNode coordinates = requireCoordinates(json);
//        LineString[] lineStrings = new LineString[coordinates.size()];
//        for (int i = 0; i < coordinates.size(); ++i) {
//            lineStrings[i] = getGeometryFactory().createLineString(decodeCoordinates(toList(coordinates.get(i))));
//        }
//        return getGeometryFactory().createMultiLineString(lineStrings);
//    }
//
//    @Override
//    public LineString decodeLineString(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        Coordinate[] coordinates = decodeCoordinates(requireCoordinates(json));
//        return getGeometryFactory().createLineString(coordinates);
//    }
//
//    @Override
//    public MultiPoint decodeMultiPoint(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        Coordinate[] coordinates = decodeCoordinates(requireCoordinates(json));
//        return getGeometryFactory().createMultiPointFromCoords(coordinates);
//    }
//
//    @Override
//    public Point decodePoint(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        Coordinate parsed = decodeCoordinate(requireCoordinates(json));
//        return getGeometryFactory().createPoint(parsed);
//    }
//
//    @Override
//    public Polygon decodePolygon(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        ArrayNode coordinates = requireCoordinates(json);
//        return decodePolygonCoordinates(coordinates);
//    }
//
//    @Override
//    public MultiPolygon decodeMultiPolygon(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        ArrayNode coordinates = requireCoordinates(json);
//        Polygon[] polygons = new Polygon[coordinates.size()];
//        for (int i = 0; i < coordinates.size(); ++i) {
//            polygons[i] = decodePolygonCoordinates(toList(coordinates.get(i)));
//        }
//        return getGeometryFactory().createMultiPolygon(polygons);
//    }
//
//    @Override
//    public GeometryCollection decodeGeometryCollection(GeometryOuterClass.GeometryCollection json) throws GeometryConverterException {
//        if (!json.has(GEOMETRIES_KEY)) {
//            throw new GeometryConverterException("missing 'geometries' field");
//        }
//        ArrayNode geometries = toList(json.get(GEOMETRIES_KEY));
//        Geometry[] geoms = new Geometry[geometries.size()];
//        for (int i = 0; i < geometries.size(); ++i) {
//            geoms[i] = decodeGeometry(geometries.get(i));
//        }
//        return getGeometryFactory().createGeometryCollection(geoms);
//    }
}
