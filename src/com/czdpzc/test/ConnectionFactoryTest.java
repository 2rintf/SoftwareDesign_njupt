package com.czdpzc.test;

import com.czdpzc.utill.ConnectionFactory;

import java.sql.Connection;

public class ConnectionFactoryTest {
    public static void main(String []args) throws Exception{
        ConnectionFactory cf = ConnectionFactory.getInstance();

        Connection conn = cf.makeConnection();

        System.out.println(conn.getAutoCommit());
    }
}
