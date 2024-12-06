package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * A utility program to convert between ASCII, Braille bit strings, and Unicode Braille characters.
 *
 * Usage:
 * - `java BrailleASCII braille <input>`: Converts ASCII text to Braille bit strings.
 * - `java BrailleASCII ascii <input>`: Converts Braille bit strings to ASCII text.
 * - `java BrailleASCII unicode <input>`: Converts Braille bit strings to Unicode Braille characters.
 *
 * Command-line arguments:
 * - `target`: The target conversion type. One of `braille`, `ascii`, or `unicode`.
 * - `source`: The input string to be converted.
 *
 * Example:
 * ```
 * java BrailleASCII braille hello
 * java BrailleASCII ascii 110010100010111000111000101010
 * java BrailleASCII unicode 110010100010111000111000101010
 * ```
 *
 * @author Moise Milenge
 */
public class BrailleASCII {
  /**
   * Translates between ASCII, Braille bit strings, and Unicode Braille characters.
   *
   * @param args Command-line arguments: [target-character-set, source-text].
   *             <ul>
   *               <li>target: The desired output format. Acceptable values are `braille`, `ascii`, or `unicode`.</li>
   *               <li>source: The input string to be converted.</li>
   *             </ul>
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Validate arguments.
    if (args.length < 2) {
      pen.println("Usage: java BrailleASCII <target> <source>");
      pen.println("  target: braille | ascii | unicode");
      pen.println("  source: The text or bit string to convert.");
      pen.close();
      return;
    }

    // Parse arguments.
    String target = args[0].toLowerCase(); // Conversion target type
    String source = args[1];              // Input string

    // Perform conversion based on target type.
    try {
      switch (target) {
        case "braille":
          // Convert ASCII text to Braille bit strings.
          for (char c : source.toCharArray()) {
            String braille = BrailleAsciiTables.toBraille(c);
            pen.print(braille);
          }
          pen.println();
          break;

        case "ascii":
          // Convert Braille bit strings to ASCII text.
          String[] brailleBits = source.split("(?<=\\G......)"); // Split input into 6-bit chunks.
          for (String bits : brailleBits) {
            String ascii = BrailleAsciiTables.toAscii(bits);
            pen.print(ascii);
          }
          pen.println();
          break;

        case "unicode":
          // Convert Braille bit strings to Unicode Braille characters.
          String[] unicodeBits = source.split("(?<=\\G......)"); // Split input into 6-bit chunks.
          for (String bits : unicodeBits) {
            String unicode = BrailleAsciiTables.toUnicode(bits);
            pen.print(unicode);
          }
          pen.println();
          break;

        default:
          pen.println("Invalid target character set. Use 'braille', 'ascii', or 'unicode'.");
      }
    } catch (Exception e) {
      pen.println("Error during conversion: " + e.getMessage());
    } finally {
      pen.close();
    }
  } // main(String[])

} // class BrailleASCII
