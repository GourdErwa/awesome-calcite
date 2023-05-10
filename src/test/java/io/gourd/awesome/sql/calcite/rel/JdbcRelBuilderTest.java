package io.gourd.awesome.sql.calcite.rel;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.core.JoinRelType;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.tools.RelBuilder;
import org.junit.jupiter.api.Test;

/**
 * The type Rel builder test.
 *
 * @author Li.Wei by 2023/3/9
 */
@Slf4j
public class JdbcRelBuilderTest {

    /**
     * Creates a relational expression for a table scan.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT * FROM emp</pre></blockquote>
     */
    private static RelBuilder example1(RelBuilder builder) {
        return builder.scan("EMP");
    }

    /**
     * Creates a relational expression for a table scan and project.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT deptno, ename FROM emp</pre></blockquote>
     */
    private static RelBuilder example2(RelBuilder builder) {
        return builder.scan("EMP").project(builder.field("DEPTNO"), builder.field("ENAME"));
    }

    private static RelBuilder example3(RelBuilder builder) {
        return builder.scan("EMP")
                .aggregate(
                        builder.groupKey("DEPTNO"),
                        builder.count(false, "C"),
                        builder.sum(false, "S", builder.field("SAL")))
                .filter(builder.call(SqlStdOperatorTable.GREATER_THAN, builder.field("C"), builder.literal(10)));
    }

    private static RelBuilder example4(RelBuilder builder) {
        final RelNode left = builder.scan("CUSTOMERS")
                .scan("ORDERS")
                .join(JoinRelType.INNER, "ORDER_ID")
                .build();

        final RelNode right = builder.scan("LINE_ITEMS")
                .scan("PRODUCTS")
                .join(JoinRelType.INNER, "PRODUCT_ID")
                .build();

        return builder.push(left).push(right).join(JoinRelType.INNER, "ORDER_ID");
    }

    /**
     * Creates a relational expression for a table scan.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT * FROM emp</pre></blockquote>
     */
    @Test
    void example0() throws Exception {
        final RelBuilder builder = RelBuilder.create(FrameworksTest.config().build());
        final RelNode root = builder.scan("norn_bi", "datasource").project().build();
        LOG.info("-----");
        LOG.info(RelOptUtil.toString(root));
    }
}
