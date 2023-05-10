package io.gourd.awesome.sql.calcite.rel;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Programs;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Rel builder test.
 *
 * @author Li.Wei by 2023/3/9
 */
public class FrameworksTest {

    private static void regJdbcSchemas(SchemaPlus rootSchema) throws Exception {
        Properties properties = new Properties();
        properties.put(
                "url",
                "jdbc:mysql://10.0.59.63:13306/norn_bi?useUnicode=true&characterEncoding=utf8&"
                        + "zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        properties.put("username", "root");
        properties.put("driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.put("password", "l0dljlxgVF8Cd2bJ");

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        final JdbcSchema jdbcSchema = JdbcSchema.create(rootSchema, "norn_bi", dataSource, null, "norn_bi");
        rootSchema.add("norn_bi", jdbcSchema);
    }

    static CalciteConnection calciteConnection() {
        try {
            Class.forName("org.apache.calcite.jdbc.Driver");
            Properties info = new Properties();
            info.setProperty("lex", "JAVA");
            Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
            return connection.unwrap(CalciteConnection.class);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Frameworks.ConfigBuilder config() throws Exception {
        final SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        regJdbcSchemas(rootSchema);
        return Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.Config.DEFAULT)
                .defaultSchema(rootSchema)
                .programs(Programs.heuristicJoinOrder(Programs.RULE_SET, true, 2));
    }

    /**
     * Data source config framework config.
     *
     * @return the framework config
     */
    static Frameworks.ConfigBuilder expandingConfig(Connection connection) throws Exception {
        final CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        regJdbcSchemas(rootSchema);
        return Frameworks.newConfigBuilder().defaultSchema(rootSchema);
    }
}
