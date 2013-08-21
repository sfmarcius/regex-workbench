/*
 * regex-workbench - Copyright (C) 2012 - Marcius da Silva da Fonseca
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

/**
 * <p>Some usefull methods to manipulate regular expressions.
 * 
 * @author Marcius da Silva da Fonseca (sf.marcius@gmail.com)
 * @version 1.0
 */
public abstract class RegexUtils {

    /**
     * <p>Transforms the escaping chars typed on a textfield to its representing char.
     * <p>Basically, when one type "\n" on a swing text component, it will escape the "\" and the underlying
     * text will be "\\n" in fact. This way, we must ignore this escaping char inserted automatically before
     * passing the entered regex to Pattern.compile().
     * <p>This occurrs with \n, \r, \t, \f and \"
     * 
     * @param input The input text that returns from JTextField.getText().
     * @return      The input with proper escaping control chars.
     */
    public static String escapeControlChars(final String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder output = new StringBuilder(input.length());
        StringBuilder backslashBuffer = new StringBuilder();
        int i = 0;
        do {
            while (input.charAt(i) == '\\') {
                backslashBuffer.append(input.charAt(i));
                i++;
            }
            char current = input.charAt(i);
            int nSlashes = backslashBuffer.length();
            if (nSlashes > 0) {
                if (nSlashes % 2 == 0) {
                    output.append(backslashBuffer);
                    output.append(current);
                } else {
                    output.append(backslashBuffer.subSequence(0, nSlashes - 1));
                    switch (current) {
                        case 'n':
                            output.append('\n');
                            break;
                        case 'r':
                            output.append('\r');
                            break;
                        case 't':
                            output.append('\t');
                            break;
                        case 'f':
                            output.append('\f');
                            break;
                        case '"':
                            output.append("\"");
                            break;
                        default:
                            output.append('\\').append(current);
                            break;
                    }
                }
                backslashBuffer.delete(0, nSlashes);
            } else {
                output.append(current);
            }
            i++;
        } while (i < input.length());
        return output.toString();
    }

    /**
     * <p>Transforms the control chars to its textual representations.
     * <p>Basically, transforms the nonprintable chars like \n, \r, \t, \f to a text that can be printed.
     * 
     * @param input The input text that returns from Pattern.getPattern().
     * @return      The input with proper showing of control chars.
     */
    public static String showControlChars(final String input) {
        String output = input.replace("\\", "\\\\").
                replace("\n", "\\n").
                replace("\r", "\\r").
                replace("\t", "\\t").
                replace("\f", "\\f").
                replace("\"", "\\\"");
        return output.toString();
    }
}
