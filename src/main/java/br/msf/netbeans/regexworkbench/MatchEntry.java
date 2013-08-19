/**
 * regex-workbench: - Copyright (C) 2012 - Marcius da Silva da Fonseca
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package br.msf.netbeans.regexworkbench;

import java.io.Serializable;

/**
 * <p>A simple object to hold the Matcher.find() coordinates, to post processing.
 * 
 * @author Marcius da Silva da Fonseca (sf.marcius@gmail.com)
 * @version 1.0
 */
public class MatchEntry implements Serializable, Comparable<MatchEntry> {

    private final int start;
    private final int end;

    public MatchEntry(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(final MatchEntry o) {
        assert o != null;
        int result = this.getStart() - o.getStart();
        return result == 0 ? this.getEnd() - o.getEnd() : result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.start;
        hash = 53 * hash + this.end;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MatchEntry other = (MatchEntry) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        return true;
    }
}
