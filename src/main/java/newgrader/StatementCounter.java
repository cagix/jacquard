package newgrader;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithStatements;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * A counter to test whether the number of occurrences of a given statement
 * type is within the specified range.
 */
public class StatementCounter extends Counter {
    private final VoidVisitorAdapter<MutableInteger> adapter;

    /**
     * Creates a new counter to test whether the number of occurrences of
     * statement type is within the specified range.
     *
     * @param name     the name of this processor (for the {@link Result})
     * @param maxScore the score if the condition holds
     * @param minCount the minimum number of occurrences
     * @param maxCount the maximum number of occurrences, or {@link Integer#MAX_VALUE}
     *                 if there is no limit.
     * @param clazz    the statement class
     * @throws IllegalArgumentException if minCount < 0 or maxCount < minCount,
     *                                  or if minCount is 0 when maxCount is {@link Integer#MAX_VALUE}
     */
    public StatementCounter(String name, int maxScore, int minCount, int maxCount, Class<? extends Statement> clazz) {
        super(name, clazz.getSimpleName(), maxScore, minCount, maxCount);
        adapter = new StatementAdapter(clazz);
    }

    @Override
    protected VoidVisitorAdapter<MutableInteger> getAdapter() {
        return adapter;
    }

    private static class StatementAdapter extends VoidVisitorAdapter<MutableInteger> {
        private final Class<? extends Statement> clazz;

        private StatementAdapter(Class<? extends Statement> clazz) {
            this.clazz = clazz;
        }

        private void count(NodeWithStatements<? extends Node> node, MutableInteger mi) {
            for (Statement statement : node.getStatements()) {
                if (clazz.isInstance(statement)) {
                    mi.increment();
                }
            }
        }

        @Override
        public void visit(BlockStmt node, MutableInteger mi) {
            count(node, mi);
        }

        @Override
        public void visit(SwitchEntry node, MutableInteger mi) {
            count(node, mi);
        }

        @Override
        public void visit(LabeledStmt ls, MutableInteger mi) {
            if (clazz.isInstance(ls.getStatement())) {
                mi.increment();
            }
        }
    }
}