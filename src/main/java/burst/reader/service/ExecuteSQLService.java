package burst.reader.service;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-4
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class ExecuteSQLService {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Transactional
    public int executeNonQuery(String sqlString) throws SQLException {
        return sqlMapClient.update("ExecuteSQLDao.executeNonQuery", sqlString);
    }

    @Transactional
    public List<Map> execute(String sqlString) throws SQLException {
        return sqlMapClient.queryForList("ExecuteSQLDao.execute", sqlString);
    }

}
