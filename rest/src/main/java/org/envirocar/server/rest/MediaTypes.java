/*
 * Copyright (C) 2013-2018 The enviroCar project
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
package org.envirocar.server.rest;

import javax.ws.rs.core.MediaType;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 * @author Arne de Wall
 */
public interface MediaTypes {
    String SCHEMA_ATTRIBUTE = "schema";
    String ROOT = "application/json; schema=\"" + Schemas.ROOT + "\"";
    MediaType ROOT_TYPE = MediaType.valueOf(ROOT);
    String USERS = "application/json; schema=\"" + Schemas.USERS + "\"";
    MediaType USERS_TYPE = MediaType.valueOf(USERS);
    String USER = "application/json; schema=\"" + Schemas.USER + "\"";
    MediaType USER_TYPE = MediaType.valueOf(USER);
    String USER_MODIFY = "application/json; schema=\"" + Schemas.USER_MODIFY + "\"";
    MediaType USER_MODIFY_TYPE = MediaType.valueOf(USER_MODIFY);
    String USER_CREATE = "application/json; schema=\"" + Schemas.USER_CREATE + "\"";
    MediaType USER_CREATE_TYPE = MediaType.valueOf(USER_CREATE);
    String USER_REF = "application/json; schema=\"" + Schemas.USER_REF + "\"";
    MediaType USER_REF_TYPE = MediaType.valueOf(USER_REF);
    String GROUPS = "application/json; schema=\"" + Schemas.GROUPS + "\"";
    MediaType GROUPS_TYPE = MediaType.valueOf(GROUPS);
    String GROUP = "application/json; schema=\"" + Schemas.GROUP + "\"";
    MediaType GROUP_TYPE = MediaType.valueOf(GROUP);
    String GROUP_MODIFY = "application/json; schema=\"" + Schemas.GROUP_MODIFY + "\"";
    MediaType GROUP_MODIFY_TYPE = MediaType.valueOf(GROUP_MODIFY);
    String GROUP_CREATE = "application/json; schema=\"" + Schemas.GROUP_CREATE + "\"";
    MediaType GROUP_CREATE_TYPE = MediaType.valueOf(GROUP_CREATE);
    String GROUP_REF = "application/json; schema=\"" + Schemas.GROUP_REF + "\"";
    MediaType GROUP_REF_TYPE = MediaType.valueOf(GROUP_REF);
    String TRACK = "application/json; schema=\"" + Schemas.TRACK + "\"";
    MediaType TRACK_TYPE = MediaType.valueOf(TRACK);
    String TRACKS = "application/json; schema=\"" + Schemas.TRACKS + "\"";
    MediaType TRACKS_TYPE = MediaType.valueOf(TRACKS);
    String TRACK_CREATE = "application/json; schema=\"" + Schemas.TRACK_CREATE + "\"";
    MediaType TRACK_CREATE_TYPE = MediaType.valueOf(TRACK_CREATE);
    String TRACK_MODIFY = "application/json; schema=\"" + Schemas.TRACK_MODIFY + "\"";
    MediaType TRACK_MODIFY_TYPE = MediaType.valueOf(TRACK_MODIFY);
    String MEASUREMENT = "application/json; schema=\"" + Schemas.MEASUREMENT + "\"";
    MediaType MEASUREMENT_TYPE = MediaType.valueOf(MEASUREMENT);
    String MEASUREMENTS = "application/json; schema=\"" + Schemas.MEASUREMENTS + "\"";
    MediaType MEASUREMENTS_TYPE = MediaType.valueOf(MEASUREMENTS);
    String MEASUREMENT_CREATE = "application/json; schema=\"" + Schemas.MEASUREMENT_CREATE + "\"";
    MediaType MEASUREMENT_CREATE_TYPE = MediaType.valueOf(MEASUREMENT_CREATE);
    String SENSOR = "application/json; schema=\"" + Schemas.SENSOR + "\"";
    MediaType SENSOR_TYPE = MediaType.valueOf(SENSOR);
    String SENSORS = "application/json; schema=\"" + Schemas.SENSORS + "\"";
    MediaType SENSORS_TYPE = MediaType.valueOf(SENSORS);
    String SENSOR_CREATE = "application/json; schema=\"" + Schemas.SENSOR_CREATE + "\"";
    MediaType SENSOR_CREATE_TYPE = MediaType.valueOf(SENSOR_CREATE);
    String SENSOR_MODIFY = "application/json; schema=\"" + Schemas.SENSOR_MODIFY + "\"";
    MediaType SENSOR_MODIFY_TYPE = MediaType.valueOf(SENSOR_MODIFY);
    String PHENOMENON = "application/json; schema=\"" + Schemas.PHENOMENON + "\"";
    MediaType PHENOMENON_TYPE = MediaType.valueOf(PHENOMENON);
    String PHENOMENONS = "application/json; schema=\"" + Schemas.PHENOMENONS + "\"";
    MediaType PHENOMENONS_TYPE = MediaType.valueOf(PHENOMENONS);
    String PHENOMENON_CREATE = "application/json; schema=\"" + Schemas.PHENOMENON_CREATE + "\"";
    MediaType PHENOMENON_CREATE_TYPE = MediaType.valueOf(PHENOMENON_CREATE);
    String STATISTIC = "application/json; schema=\"" + Schemas.STATISTIC + "\"";
    MediaType STATISTIC_TYPE = MediaType.valueOf(STATISTIC);
    String STATISTICS = "application/json; schema=\"" + Schemas.STATISTICS + "\"";
    MediaType STATISTICS_TYPE = MediaType.valueOf(STATISTICS);
    String ACTIVITY = "application/json; schema=\"" + Schemas.ACTIVITY + "\"";
    MediaType ACTIVITY_TYPE = MediaType.valueOf(ACTIVITY);
    String ACTIVITIES = "application/json; schema=\"" + Schemas.ACTIVITIES + "\"";
    MediaType ACTIVITIES_TYPE = MediaType.valueOf(ACTIVITIES);
    String XML_RDF = "application/rdf+xml";
    MediaType XML_RDF_TYPE = MediaType.valueOf(XML_RDF);
    String APPLICATION_ZIPPED_SHP = "application/x-zipped-shp";
    MediaType APPLICATION_ZIPPED_SHP_TYPE = MediaType.valueOf(APPLICATION_ZIPPED_SHP);
    String TEXT_CSV = "text/csv";
    MediaType TEXT_CSV_TYPE = MediaType.valueOf(TEXT_CSV);
    String TURTLE = "text/turtle";
    MediaType TURTLE_TYPE = MediaType.valueOf(TURTLE);
    String TURTLE_ALT = "application/x-turtle";
    MediaType TURTLE_ALT_TYPE = MediaType.valueOf(TURTLE_ALT);
    String IMAGE_JPEG = "image/jpeg";
    MediaType IMAGE_JPEG_TYPE = MediaType.valueOf(IMAGE_JPEG);
    String TERMS_OF_USE = "application/json; schema=\"" + Schemas.TERMS_OF_USE + "\"";
    MediaType TERMS_OF_USE_TYPE = MediaType.valueOf(TERMS_OF_USE);
    String TERMS_OF_USE_INSTANCE = "application/json; schema=\"" + Schemas.TERMS_OF_USE_INSTANCE + "\"";
    MediaType TERMS_OF_USE_INSTANCE_TYPE = MediaType.valueOf(TERMS_OF_USE_INSTANCE);
    String ANNOUNCEMENTS = "application/json; schema=\"" + Schemas.ANNOUNCEMENTS + "\"";
    MediaType ANNOUNCEMENTS_TYPE = MediaType.valueOf(ANNOUNCEMENTS);
    String ANNOUNCEMENT = "application/json; schema=\"" + Schemas.ANNOUNCEMENT + "\"";
    MediaType ANNOUNCEMENT_TYPE = MediaType.valueOf(ANNOUNCEMENT);
    String BADGES = "application/json; schema=\"" + Schemas.BADGES + "\"";
    MediaType BADGES_TYPE = MediaType.valueOf(BADGES);
    String BADGE = "application/json; schema=\"" + Schemas.BADGE + "\"";
    MediaType BADGE_TYPE = MediaType.valueOf(BADGE);
    String PASSWORD_RESET_REQUEST = "application/json; schema=\"" + Schemas.PASSWORD_RESET_REQUEST + "\"";
    MediaType PASSWORD_RESET_REQUEST_TYPE = MediaType.valueOf(PASSWORD_RESET_REQUEST);
    String PASSWORD_RESET_VERIFICATION = "application/json; schema=\"" + Schemas.PASSWORD_RESET_VERIFICATION + "\"";
    MediaType PASSWORD_RESET_VERIFICATION_TYPE = MediaType.valueOf(PASSWORD_RESET_VERIFICATION);
    String FUELINGS = "application/json; schema=\"" + Schemas.FUELINGS + "\"";
    MediaType FUELINGS_TYPE = MediaType.valueOf(FUELINGS);
    String FUELING = "application/json; schema=\"" + Schemas.FUELING + "\"";
    MediaType FUELING_TYPE = MediaType.valueOf(FUELING);
    String FUELING_CREATE = "application/json; schema=\"" + Schemas.FUELING_CREATE + "\"";
    MediaType FUELING_CREATE_TYPE = MediaType.valueOf(FUELING_CREATE);
    String PNG_IMAGE = "image/png";
    MediaType PNG_IMAGE_TYPE = MediaType.valueOf(PNG_IMAGE);
    String USERSTATISTIC = "application/json; schema=\"" + Schemas.USERSTATISTIC + "\"";
    MediaType USERSTATISTIC_TYPE = MediaType.valueOf(USERSTATISTIC);
    String PRIVACY_STATEMENTS = "application/json; schema=\"" + Schemas.PRIVACY_STATEMENTS + "\"";
    MediaType PRIVACY_STATEMENTS_TYPE = MediaType.valueOf(PRIVACY_STATEMENTS);
    String PRIVACY_STATEMENT = "application/json; schema=\"" + Schemas.PRIVACY_STATEMENT + "\"";
    MediaType PRIVACY_STATEMENT_TYPE = MediaType.valueOf(PRIVACY_STATEMENT);
}
