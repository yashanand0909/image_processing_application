package model.operations.colortransformation;

/**
 * This class represents a greyscale color transformation operation that can be applied to an image.
 */
public class Greyscale extends CommonColorTransformOperation {
  private final double[][] greymatrix;

  /**
   * Constructs a greyscale color transformation object.
   */
  public Greyscale() {
    this.greymatrix = new double[][]{{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},{0.2126, 0.7152, 0.0722}};
  }

  @Override
  public double[][] getTransformCoefficient() {
    return greymatrix;
  }
}
