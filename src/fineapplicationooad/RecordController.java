/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Hamza
 */
public class RecordController
{

    RecordView view;
    RecordModel model;
    Database dao;
    SearchBarController searchBarCtrl;

    public RecordController(RecordView view, RecordModel model, Database dao)
    {
        searchBarCtrl=new SearchBarController(new SearchBarView(), new SearchBarModel(),dao);
        this.view = view;
        this.model = model;
        this.dao = dao;
        
        
        view.add(searchBarCtrl.getView(), BorderLayout.WEST);

        
    }

    public Database getDao()
    {
        return dao;
    }

    public void setDao(Database dao)
    {
        this.dao = dao;
    }

    public RecordModel getModel()
    {
        return model;
    }

    public void setModel(RecordModel model)
    {
        this.model = model;
    }

    public RecordView getView()
    {
        return view;
    }

    public void setView(RecordView view)
    {
        this.view = view;
    }
    void updateCustomersInSearchBar()
    {
//        searchBarCtrl.view.searchList.setListData(listData);
        searchBarCtrl.updateCustomerList();
        
    }
    
    
    
   
}
