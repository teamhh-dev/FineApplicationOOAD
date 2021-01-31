/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hamza
 */
public class Database
{

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    Boolean databaseStatus = false;

    public Database()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            //first configuring database if not configured
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");

            statement = connection.createStatement();
            initDatabase();

        } catch (Exception e)
        {
            System.out.println("No Database Connectivity!");
            return;
        }
        databaseStatus = true;
    }

    private void initDatabase()
    {
        String createHamzaAliDatabase = "Create Database if not exists hamzaalidatabase";
        String createCustomerTable = "Create table if not exists hamzaalidatabase.customer("
                + "name varchar(20) primary key,"
                + "address varchar(50),"
                + "phoneNo varchar(20),"
                + "description varchar(100)"
                + ");";

        String createTransactionTable = "CREATE TABLE IF NOT EXISTS hamzaalidatabase.transactions(customerName VARCHAR(20) NOT NULL,transactionType CHAR(1) NOT NULL, transactionDateTime DATETIME NOT NULL, amount INT(15) NOT NULL, transactionDesc VARCHAR(50) DEFAULT('None'),CONSTRAINT trans_type_cons check (transactionType='P' or transactionType='B'));";

        try
        {
            statement.executeUpdate(createHamzaAliDatabase);

            statement.executeUpdate(createCustomerTable);
            statement.executeUpdate(createTransactionTable);

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hamzaalidatabase", "root", "");
            statement = connection.createStatement();

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addCustomer(CustomerModel model)
    {

        String addCustomerQuery = "insert into hamzaalidatabase.customer(name,address,phoneNo,description) values" + "('" + model.getCustomerName() + "','" + model.getCustomerAddress() + "','" + model.getCustomerPhoneNo() + "','" + model.getCustomerDescription() + "')";

        try
        {
            statement.executeUpdate(addCustomerQuery);
        } catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public Vector<CustomerModel> getAllCustomers()
    {
        Vector<CustomerModel> customersName = new Vector<>();

        String selectCustomers = "select * from hamzaalidatabase.customer";

        try
        {
            resultSet = statement.executeQuery(selectCustomers);
            while (resultSet.next())
            {
                customersName.add(new CustomerModel(resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("phoneNo"), resultSet.getString("description")));
            }
        } catch (Exception ex)
        {
            CustomerModel customer = new CustomerModel("No Database Conectivity!!", "No", "No", "No");

            customersName.add(customer);
        }

        return customersName;
    }

    public DefaultTableModel getCustomerRecord(String customerName)
    {
        String selectCustomerRecordQuery = "SELECT\n"
                + "    t.date,\n"
                + "    t.Description,\n"
                + "    CASE WHEN t.transactionType = 'B' THEN amount ELSE 0\n"
                + "END AS BILL,\n"
                + "CASE WHEN t.transactionType = 'P' THEN amount ELSE 0\n"
                + "END AS PAYMENT,\n"
                + "CASE WHEN t.transactionType = 'P' THEN @running_total := @running_total - amount ELSE @running_total :=  @running_total + amount\n"
                + "END AS \"Running Total\"\n"
                + "FROM\n"
                + "    (\n"
                + "    SELECT\n"
                + "        DATE_FORMAT(transactionDateTime,'%M %d %Y@%H:%i') AS DATE,\n"
                + "        transactionDesc AS Description,\n"
                + "        amount AS AMOUNT,\n"
                + "        transactionType\n"
                + "    FROM\n"
                + "        transactions\n"
                + "    WHERE\n"
                + "        customerName ='" + customerName + "'\n"
                + ") t\n"
                + "JOIN(\n"
                + "SELECT\n"
                + "    @running_total := 0\n"
                + ") r\n"
                + "ORDER BY\n"
                + "    t.date";
//                
        String[][] data = null;
        try
        {
//            System.out.println(selectCustomerRecordQuery);
            resultSet = statement.executeQuery(selectCustomerRecordQuery);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        try
        {
            return buildTableModel(resultSet);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public DefaultTableModel getAllTransaction(String customerName)
    {
        String getTransactionsQuery = "SELECT\n"
                + "    `customerName` AS \"Customer Name\",\n"
                + "    DATE_FORMAT(\n"
                + "        `transactionDateTime`,\n"
                + "        '%M %d %Y@%H:%i'\n"
                + "    ) AS \"Transactioon Date\",\n"
                + "    CASE WHEN `transactionType` = 'B' THEN 'Bill' WHEN `transactionType` = 'P' THEN 'Payment'\n"
                + "END as \"Transaction Type \",`amount` as \"Transaction Amount\",\n"
                + "`transactionDesc` as \"Transaction Decription\"\n"
                + "FROM\n"
                + "    `transactions`\n"
                + "WHERE\n"
                + "    customerName = '" + customerName + "'\n"
                + "ORDER BY\n"
                + "    transactionDateTime\n"
                + "DESC\n";

        try
        {
            resultSet = statement.executeQuery(getTransactionsQuery);
            return buildTableModel(resultSet);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException
    {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++)
        {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next())
        {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    boolean addTransaction(TransactionModel model)
    {

        String addTransactionSql = "Insert into hamzaalidatabase.transactions(customerName,transactionType,transactionDateTime,amount,transactionDesc) values(";

        if (model.getType() == TransactionModel.PaymentType.bill)
        {
            addTransactionSql += ("'" + model.getCustomerName() + "',");
            addTransactionSql += ("'" + "B" + "',");
            addTransactionSql += ("STR_TO_DATE('" + model.getCurrentDateTime() + "','%d-%m-%Y %H:%i:%s'),");
            addTransactionSql += (model.getAmount() + ",");
            addTransactionSql += ("'" + model.getDescription() + "')");
            try
            {
                System.out.println(addTransactionSql);

                statement.executeUpdate(addTransactionSql);
            } catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
                return false;

            }
        } else
        {
            addTransactionSql += ("'" + model.getCustomerName() + "',");
            addTransactionSql += ("'" + "P" + "',");
            addTransactionSql += ("STR_TO_DATE('" + model.getCurrentDateTime() + "','%d-%m-%Y %H:%i:%s'),");
            addTransactionSql += (model.getAmount() + ",");
            addTransactionSql += ("'" + model.getDescription() + "')");
            try
            {
                statement.executeUpdate(addTransactionSql);
            } catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                return false;
            }
            System.out.println(addTransactionSql);
        }

        return true;
    }

    boolean deleteTransaction(TransactionModel model)
    {
        String deleteTransactionQuery
                = "delete from transactions where "
                + "customerName='" + model.getCustomerName() + "'"
                + " and DATE_FORMAT(transactionDateTime,'%M %d %Y@%H:%i')=" + "DATE_FORMAT(STR_TO_DATE('" + model.getCurrentDateTime() + "','%M %d %Y@%H:%i'),'%M %d %Y@%H:%i')"
                + "and transactionType=";

        if (model.getType() == TransactionModel.PaymentType.bill)
        {
            deleteTransactionQuery += "'B'";
        } else
        {
            deleteTransactionQuery += "'P'";

        }
        System.out.println(deleteTransactionQuery);
        try
        {
            statement.execute(deleteTransactionQuery);
        } catch (Exception ex)
        {

            return false;
        }
        return true;
    }
}
