package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Moise Milenge
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** Depth of the tree. */
  private final int depth;

  /** Root node of the tree. */
  private final BitTreeNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new BitTree for sequences of `n` bits.
   *
   * @param n the depth of the tree
   */
  public BitTree(int n) {
    this.depth = n;
    this.root = new BitTreeInteriorNode(); // Initialize with an empty root node
  } // end of BitTree(int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Set a value for a given bit string.
   *
   * @param bits  the bit string
   * @param value the value to associate with the bit string
   */
  public void set(String bits, String value) {
    if (bits.length() != depth) {
      throw new IndexOutOfBoundsException("Invalid bit string length: " + bits);
    }

    BitTreeNode current = root;
    for (int i = 0; i < bits.length(); i++) {
      int bit = bits.charAt(i) - '0'; // Convert '0' or '1' to integer
      if (bit != 0 && bit != 1) {
        throw new IndexOutOfBoundsException("Invalid character in bit string: " + bits);
      }
      if (current.getChild(bit) == null) {
        if (i == bits.length() - 1) {
          current.setChild(bit, new BitTreeLeaf(value)); // Final node is a leaf
        } else {
          current.setChild(bit, new BitTreeInteriorNode()); // Interior node
        } // end if-else
      }
      current = current.getChild(bit);
    } // end for

    if (current instanceof BitTreeLeaf bitTreeLeaf) {
          bitTreeLeaf.setValue(value);
    } // end if
  } // end of set(String, String)

  /**
   * Get the value for a given bit string.
   *
   * @param bits the bit string
   * @return the value associated with the bit string
   */
  public String get(String bits) {
    if (bits.length() != depth) {
      throw new IndexOutOfBoundsException("Invalid bit string length: " + bits);
    }

    BitTreeNode current = root;
    for (int i = 0; i < bits.length(); i++) {
      int bit = bits.charAt(i) - '0';
      if (bit != 0 && bit != 1) {
        throw new IndexOutOfBoundsException("Invalid character in bit string: " + bits);
      }

      current = current.getChild(bit);
      if (current == null) {
        throw new IndexOutOfBoundsException("No value for given bit string: " + bits);
      } // end if
    } // end for

    if (current instanceof BitTreeLeaf bitTreeLeaf) {
      return bitTreeLeaf.getValue();
    } // end if
    throw new IndexOutOfBoundsException("No value for given bit string: " + bits);
  } // end of get(String)

  /**
   * Dump the contents of the tree to a writer in CSV format.
   *
   * @param pen the writer to print to
   */
  public void dump(PrintWriter pen) {
    dumpHelper(root, "", pen);
  } // end of dump(PrintWriter)

  /**
   * Helper method to recursively dump the tree.
   *
   * @param node  the current node
   * @param bits  the bit string so far
   * @param pen   the writer to print to
   */
  private void dumpHelper(BitTreeNode node, String bits, PrintWriter pen) {
    if (node == null) {
      return;
    } // end if

    if (node instanceof BitTreeLeaf bitTreeLeaf) {
      pen.println(bits + "," + bitTreeLeaf.getValue());
    } else {
      dumpHelper(node.getChild(0), bits + "0", pen);
      dumpHelper(node.getChild(1), bits + "1", pen);
    } // end if-else
  } // end of dumpHelper(BitTreeNode, String, PrintWriter)

  /**
   * Load the tree from an input stream in CSV format.
   *
   * @param source the input stream
   */
  public void load(InputStream source) {
    try (Scanner scanner = new Scanner(source)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
          String[] parts = line.split(",", 2);
          if (parts.length == 2) {
            set(parts[0], parts[1]);
          } else {
            throw new IllegalArgumentException("Invalid input format: " + line);
          } // end if-else
        } // end if
      } // end while
      scanner.close();
    }
  } // end of load(InputStream)

  // +---------------+-----------------------------------------------
  // | Inner Classes |
  // +---------------+

  /**
   * Common interface for nodes in the BitTree.
   */
  interface BitTreeNode {
    /**
     * Get the child node at the given index.
     *
     * @param index the child index (0 or 1)
     * @return the child node
     */
    BitTreeNode getChild(int index);

    /**
     * Set the child node at the given index.
     *
     * @param index the child index (0 or 1)
     * @param child the child node to set
     */
    void setChild(int index, BitTreeNode child);
  } // end of BitTreeNode

  /**
   * Represents an interior node in the BitTree.
   */
  static class BitTreeInteriorNode implements BitTreeNode {
    private final BitTreeNode[] children = new BitTreeNode[2];

    @Override
    public BitTreeNode getChild(int index) {
      return children[index];
    } // end of getChild

    @Override
    public void setChild(int index, BitTreeNode child) {
      children[index] = child;
    } // end of setChild
  } // end of BitTreeInteriorNode

  /**
   * Represents a leaf node in the BitTree.
   */
  static class BitTreeLeaf implements BitTreeNode {
    private String value;

    /**
     * Create a new leaf node with the given value.
     *
     * @param value the value of the leaf
     */
    public BitTreeLeaf(String value) {
      this.value = value;
    } // end of BitTreeLeaf

    /**
     * Get the value of the leaf.
     *
     * @return the value
     */
    public String getValue() {
      return value;
    } // end of getValue

    /**
     * Set a new value for the leaf.
     *
     * @param value the new value
     */
    public void setValue(String value) {
      this.value = value;
    } // end of setValue

    @Override
    public BitTreeNode getChild(int index) {
      return null; // Leaf nodes do not have children
    } // end of getChild

    @Override
    public void setChild(int index, BitTreeNode child) {
      throw new UnsupportedOperationException("Leaf nodes cannot have children");
    } // end of setChild
  } // end of BitTreeLeaf
} // end of BitTree
