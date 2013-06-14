/*
 * Copyright (C) 2013  Christian Autermann, Jan Alexander Wirwahn,
 *                     Arne De Wall, Dustin Demuth, Saqib Rasheed
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
package io.car.server.rest.rights;

import io.car.server.core.entities.Group;

/**
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public interface GroupRights {
    boolean canSee(Group group);

    boolean canSeeActivitiesOf(Group group);

    boolean canJoinGroup(Group group);

    boolean canLeaveGroup(Group group);

    boolean canSeeNameOf(Group group);

    boolean canSeeDescriptionOf(Group group);

    boolean canSeeMembersOf(Group group);

    boolean canSeeOwnerOf(Group group);

    boolean canSeeCreationTimeOf(Group group);

    boolean canSeeModificationTimeOf(Group group);

    boolean canModify(Group group);

    boolean canDelete(Group group);
}
