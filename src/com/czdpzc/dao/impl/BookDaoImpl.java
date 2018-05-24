package com.czdpzc.dao.impl;

import com.czdpzc.dao.BookDAO;
import com.czdpzc.entity.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDaoImpl implements BookDAO {


    /**
     * **此处属于后台管理员操作！！**
     * 用于将新入库的图书写入数据库
     *      添加书名、分类、出版社、作者
     * @param conn
     * @param book
     * @throws SQLException
     */
    @Override
    public void save(Connection conn, Books book) throws SQLException {

        String sql = "INSERT INTO tbl_book(book_name, book_class," +
                " book_pub, book_writer) VALUES(?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,book.getBookName());
        ps.setString(2,book.getBookClass());
        ps.setString(3,book.getBookPub());
        ps.setString(4,book.getBookWriter());

        ps.execute();

    }

    /**
     * **此处属于后台管理员操作！！**
     * 根据图书ID更新图书信息
     *      更新书名、分类、出版社、作者
     *
     * @param conn
     * @param id
     * @param book
     * @throws SQLException
     */
    @Override
    public void update(Connection conn, Long id, Books book) throws SQLException {

        String sql = "UPDATE tbl_book SET book_name = ?, book_class = ?," +
                "book_pub = ?, book_writer = ? WHERE book_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,book.getBookName());
        ps.setString(2,book.getBookClass());
        ps.setString(3,book.getBookPub());
        ps.setString(4,book.getBookWriter());
        ps.setLong(5,id);

        ps.execute();
    }

    /**
     * **此处属于后台管理员操作！！**
     * 删除一本书的数据库记录，此处必须用图书ID进行操作！避免重名的图书进行误删
     *
     * @param conn
     * @param book
     * @throws SQLException
     */
    @Override
    public void delete(Connection conn, Books book) throws SQLException {

        String sql = "DELETE FROM tbl_book WHERE book_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1,book.getId());

        ps.execute();
    }


    /**
     * **此处属于通用操作！！**
     * 多属性模糊查询，不使用的值必须置空！！
     *
     * @param conn
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet get(Connection conn, Books book) throws SQLException {

        String sql = "SELECT * FROM tbl_book WHERE book_name LIKE ? OR book_class LIKE ? OR " +
                "book_pub LIKE ? OR book_writer LIKE ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"%"+book.getBookName()+"%");
        ps.setString(2,"%"+book.getBookClass()+"%");
        ps.setString(3,"%"+book.getBookPub()+"%");
        ps.setString(4,"%"+book.getBookWriter()+"%");


        return ps.executeQuery();
    }

}
