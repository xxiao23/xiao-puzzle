
public class OptimalBst {
	public double optimaBst(double[] w) {
		double[][] dp = new double[w.length][w.length];
		for (int i = 0; i < w.length; ++i) {
			dp[i][i] = w[i];
		}
		
		for (int l = 1; l < w.length; ++l) {
			for (int i = 0; i < w.length && i+l < w.length; ++i) {
				double m = Double.MAX_VALUE;
				double s = 0.0;
				for (int k = i; k <= i+l; ++k) {
					s += w[k];
				}
				for (int k = i; k <= i+l; ++k) {
					double t1 = (k-1) < 0 ? 0 : dp[i][k-1];
					double t2 = (k+1) < w.length ? dp[k+1][i+l] : 0;
					m = Math.min(m, s+t1+t2);
				}
				dp[i][i+l] = m;
			}
		}
		return dp[0][w.length-1];
	}
}
