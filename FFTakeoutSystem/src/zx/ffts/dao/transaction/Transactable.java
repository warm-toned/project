package zx.ffts.dao.transaction;

import java.sql.SQLException;

import zx.ffts.dao.SQLExecutor;

/**
 * 封装单个事务
 * 
 * @author Pain
 * 
 */
public interface Transactable {

	/**
	 * 
	 * @param executor
	 *            :用于执行sql语句的执行者类
	 * @param params
	 *            :方法中的sql语句需要的总参数列表
	 * @return
	 * @throws SQLException
	 */
	public Object transact(SQLExecutor executor, Object... params)
			throws SQLException;
}
