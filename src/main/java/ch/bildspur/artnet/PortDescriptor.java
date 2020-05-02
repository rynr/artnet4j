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

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This PortDescriptor is immutable. Once initialized, it cannot be changed. A
 * change will end up with a new version.
 */
@ToString
@EqualsAndHashCode
public class PortDescriptor {

    private static final int INPUT_FLAG = 0x40;
    private static final int OUTPUT_FLAG = 0x80;
    private final int id;

    /**
     * Creates a new {@link PortDescriptor} by it's id.
     *
     * @param id The id of the {@link PortDescriptor}.
     */
    public PortDescriptor(int id) {
        this.id = id;
    }

    /**
     * @return True, if the {@link PortDescriptor} can input data.
     */
    public boolean canInput() {
        return (id & INPUT_FLAG) > 0;
    }

    /**
     * @return True, if the {@link PortDescriptor} can output data.
     */
    public boolean canOutput() {
        return (id & OUTPUT_FLAG) > 0;
    }

    /**
     * @return {@link PortType} of the {@link PortDescriptor}.
     */
    public PortType getType() {
        return PortType.fromId(id);
    }

    public int getData() {
        return id;
    }

    /**
     * Clone of the {@link PortDescriptor} that has a specified output
     * configuration.
     *
     * @param canOutput boolean value, if the resulting {@link PortDescriptor} can
     *                  output
     * @return Clone of the original {@link PortDescriptor} that has the given
     *         output ability
     */
    public PortDescriptor setCanOutput(boolean canOutput) {
        return new PortDescriptor(canOutput ? id | OUTPUT_FLAG : id & ~OUTPUT_FLAG);
    }

    /**
     * Clone of the {@link PortDescriptor} that has a specified output
     * configuration.
     *
     * @param canInput boolean value, if the resulting {@link PortDescriptor} can
     *                 input
     * @return Clone of the original {@link PortDescriptor} that has the given input
     *         ability
     */
    public PortDescriptor setCanInput(boolean canInput) {
        return new PortDescriptor(canInput ? id | INPUT_FLAG : id & ~INPUT_FLAG);
    }

    /**
     * Clone of the {@link PortDescriptor} that has a specified {@link PortType}.
     *
     * @param type {@link PortType} that the resulting clone should be of
     * @return Clone of the original {@link PortDescriptor} that has the given
     *         {@link PortType}
     */
    public PortDescriptor setType(PortType type) {
        return new PortDescriptor((this.id & (INPUT_FLAG | OUTPUT_FLAG)) | type.getPortID());
    }
}
