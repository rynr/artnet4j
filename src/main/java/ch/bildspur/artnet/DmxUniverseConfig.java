/*
 * This file is part of artnet4j.
 * 
 * Copyright 2009 Karsten Schmidt (PostSpectacular Ltd.)
 * 
 * artnet4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * artnet4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with artnet4j. If not, see <http://www.gnu.org/licenses/>.
 */

package ch.bildspur.artnet;

import java.io.Serializable;
import java.net.InetAddress;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DmxUniverseConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final InetAddress ip;
    private final int universeID;
    private final int numDmxChannels;
    private final int serverPort;
    private final boolean ignoreNumChannels;
}