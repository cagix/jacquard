package newgrader.coverage;

import newgrader.common.Result;

/**
 * A way of converting branch coverage and line coverage information
 * into a single score.
 */
public abstract class Scorer {
    protected double maxScore;

    protected Scorer(double maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Converts branch coverage and line coverage percentages into a score.
     *
     * @param branchCoverage the percentage of branches covered [0, 1]
     * @param lineCoverage the percentage of lines covered [0, 1]
     * @return a nonnegative score
     */
    public abstract double score(double branchCoverage, double lineCoverage);

    // override this to base score on anything but branch coverage and line coverage
    protected double score(ClassInfo info) {
        return score(info.branchCoverage(), info.lineCoverage());
    }

    public abstract Result getResult(ClassInfo info);

}
