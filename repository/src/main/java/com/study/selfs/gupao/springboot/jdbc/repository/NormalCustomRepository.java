package com.study.selfs.gupao.springboot.jdbc.repository;


import com.study.selfs.gupao.springboot.jdbc.domain.NormalCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.List;

@Repository
public class NormalCustomRepository {


    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    public NormalCustomRepository(@Qualifier("masterDataSource") DataSource dataSource, @Qualifier("masterJdbcTemplate") JdbcTemplate masterJdbcTemplate, @Qualifier("masterDataSourceTransactionManager") PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.jdbcTemplate = masterJdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }


    public boolean jdbcSave(NormalCustom normalCustom){
        Connection connection = null;
        boolean isSuccess;
        System.out.printf("[Thread：%s] %s\n",Thread.currentThread().getName(),normalCustom);
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_normal_custom(name,age,sex,job,createdate) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,normalCustom.getName());
            preparedStatement.setInt(2,normalCustom.getAge());
            preparedStatement.setInt(3,normalCustom.getSex());
            preparedStatement.setString(4,normalCustom.getJob());
            preparedStatement.setDate(5,new Date(Calendar.getInstance().getTime().getTime()));

            isSuccess = preparedStatement.executeUpdate() > 0 ? Boolean.TRUE : Boolean.FALSE;
            preparedStatement.close();
        } catch (SQLException e) {
            return false;
        }finally {
            if(connection != null){
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }


    public Boolean save(NormalCustom normalCustom){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        boolean success = doSaveTrans(normalCustom);
        platformTransactionManager.commit(transactionStatus);
        return success;
    }


    @Transactional
    public Boolean transactionSave(NormalCustom normalCustom){
        return doSaveTrans(normalCustom);
    }

    private Boolean doSaveTrans(NormalCustom normalCustom){
        return jdbcTemplate.execute("INSERT INTO tb_normal_custom(name,age,sex,job,createdate) VALUES (?,?,?,?,?)", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,normalCustom.getName());
                ps.setInt(2,normalCustom.getAge());
                ps.setInt(3,normalCustom.getSex());
                ps.setString(4,normalCustom.getJob());
                ps.setDate(5,new Date(Calendar.getInstance().getTime().getTime()));
                return ps.executeUpdate() > 0;
            }
        });
    }


    public List<NormalCustom> findAllNormalCustom(){
        List<NormalCustom> normalCustoms = jdbcTemplate.query("SELECT name,age,sex,job,createdate FROM tb_normal_custom ", new RowMapper<NormalCustom>() {
            @Override
            public NormalCustom mapRow(ResultSet rs, int rowNum) throws SQLException {
                NormalCustom normalCustom = new NormalCustom();
                normalCustom.setName(rs.getString("name"));
                normalCustom.setAge(rs.getInt("age"));
                normalCustom.setCreateDate(rs.getDate("createdate"));
                normalCustom.setJob(rs.getString("job"));
                normalCustom.setSex(rs.getInt("sex"));
                return normalCustom;
            }
        });
        return normalCustoms;
    }

}
