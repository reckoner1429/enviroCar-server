/*
 * Copyright (C) 2013 The enviroCar project
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

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * 
 * 
 * @author staschc
 *
 */
public class NearPoint {
	
	private final Point point;
	private final double distance;
	
	public NearPoint(Point pointp, double distancep){
		point = pointp;
		distance = distancep;
	}
	
	public static NearPoint valueOf(String nearPointString) {
        final String[] values = nearPointString.split(",");
        if (values.length != 3) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }
        try {
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            double distance = Double.parseDouble(values[2]);
            Point p = new GeometryFactory().createPoint(new Coordinate(x,y));
            return new NearPoint(p,distance);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(e, Status.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(e, Status.BAD_REQUEST);
        }
    }
	
	public Point getPoint() {
		return point;
	}

	public double getDistance() {
		return distance;
	}

}
