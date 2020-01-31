package com.bysssss.goinmul.api.model.mysql2.member.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.bysssss.goinmul.api.common.util.CryptoUtil;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class PrivacyTypeHandler extends BaseTypeHandler<String> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, CryptoUtil.encPrivacy(parameter));
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return CryptoUtil.decPrivacy(rs.getString(columnName));
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return CryptoUtil.decPrivacy(rs.getString(columnIndex));
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return CryptoUtil.decPrivacy(cs.getString(columnIndex));
	}
}