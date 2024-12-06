package edu.grinnell.csc207.util;

/**
 * A utility class for converting between ASCII, Braille bit strings, and Unicode Braille characters.
 * Provides mappings from ASCII to Braille, Braille to ASCII, and Braille to Unicode.
 *
 * @author Moise Milenge
 */
public class BrailleAsciiTables {


  /** Mapping from Braille bit strings to ASCII. */
  static final String B2A =
      """
      100000,A
      110000,B
      100100,C
      100110,D
      100010,E
      110100,F
      110110,G
      110010,H
      010100,I
      010110,J
      101000,K
      111000,L
      101100,M
      101110,N
      101010,O
      111100,P
      111110,Q
      111010,R
      011100,S
      011110,T
      101001,U
      111001,V
      101101,X
      101111,Y
      101011,Z
      010111,W
      000000,
      """;

  /** Mapping from Braille bit strings to Unicode. */
  static final String B2U =
      "000000,2800\n"
      + "100000,2801\n"
      + "010000,2802\n"
      + "110000,2803\n"
      + "001000,2804\n"
      + "101000,2805\n"
      + "011000,2806\n"
      + "111000,2807\n"
      + "000100,2808\n"
      + "100100,2809\n"
      + "010100,280A\n"
      + "110100,280B\n"
      + "001100,280C\n"
      + "101100,280D\n"
      + "011100,280E\n"
      + "111100,280F\n"
      + "000010,2810\n"
      + "100010,2811\n"
      + "010010,2812\n"
      + "110010,2813\n"
      + "001010,2814\n"
      + "101010,2815\n"
      + "011010,2816\n"
      + "111010,2817\n"
      + "000110,2818\n"
      + "100110,2819\n"
      + "010110,281A\n"
      + "110110,281B\n"
      + "001110,281C\n"
      + "101110,281D\n"
      + "011110,281E\n"
      + "111110,281F\n";

  // +----------------+----------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Convert an ASCII character to a Braille bit string.
   *
   * @param letter The ASCII character to convert.
   * @return The corresponding Braille bit string.
   */
  public static String toBraille(char letter) {
    return ""; // Stub
  } // end of toBraille(char)

  /**
   * Convert a Braille bit string to an ASCII character.
   *
   * @param bits The Braille bit string to convert.
   * @return The corresponding ASCII character.
   */
  public static String toAscii(String bits) {
    return ""; // Stub
  } // end of toAscii(String)

  /**
   * Convert a Braille bit string to a Unicode Braille character.
   *
   * @param bits The Braille bit string to convert.
   * @return The corresponding Unicode Braille character.
   */
  public static String toUnicode(String bits) {
    return ""; // Stub
  } // end of toUnicode(String)
} // end of BrailleAsciiTables
