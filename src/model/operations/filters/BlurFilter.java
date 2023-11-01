package model.operations.filters;

/**
 * Represents a blur filter operation object.
 */
public class BlurFilter extends CommonFilterOperation {
  double[][] gaussianFilter;

  /**
   * Constructs a blur filter object.
   */
  public BlurFilter() {
    this.gaussianFilter = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
  }

  @Override
  public double[][] getFilter() {
    return gaussianFilter;
  }
}
