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
package org.envirocar.server.rest.encoding.json;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.envirocar.server.core.entities.PrivacyStatement;
import org.envirocar.server.rest.JSONConstants;
import org.envirocar.server.rest.MediaTypes;
import org.envirocar.server.rest.rights.AccessRights;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * TODO JavaDoc
 *
 * @author Matthes Rieke
 */
@Provider
public class PrivacyStatementJSONEncoder extends AbstractJSONEntityEncoder<PrivacyStatement> {

    public PrivacyStatementJSONEncoder() {
        super(PrivacyStatement.class);
    }

    @Override
    public ObjectNode encodeJSON(PrivacyStatement t, AccessRights rights, MediaType mediaType) {
        ObjectNode termsOfUse = getJsonFactory().objectNode();
        if (t.getIdentifier() != null) {
            termsOfUse.put(JSONConstants.IDENTIFIER_KEY, t.getIdentifier());
        }
        if (t.getIssuedDate() != null) {
            termsOfUse.put(JSONConstants.ISSUED_DATE, t.getIssuedDate());
        }
        if (mediaType.equals(MediaTypes.PRIVACY_STATEMENT_TYPE)) {
            if (t.hasCreationTime()) {
                termsOfUse.put(JSONConstants.CREATED_KEY, getDateTimeFormat().print(t.getCreationTime()));
            }
            if (t.hasModificationTime()) {
                termsOfUse.put(JSONConstants.MODIFIED_KEY, getDateTimeFormat().print(t.getModificationTime()));
            }
            if (t.getContents() != null) {
                termsOfUse.put(JSONConstants.CONTENTS, t.getContents());
            }
        }
        return termsOfUse;
    }
}
